package epam.chernova.finalproject.command.impl.redirect;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.entity.Account;
import epam.chernova.finalproject.entity.Client;
import epam.chernova.finalproject.exception.ServiceException;
import epam.chernova.finalproject.factory.ServiceFactory;
import epam.chernova.finalproject.util.SessionElements;
import epam.chernova.finalproject.webenum.PageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CloseOrderCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(CloseOrderCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.ORDERS;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start CloseOrderCommand");
        int idOrder,idClient;
        double totalCost;
        Account account;
        try {
            idOrder = Integer.parseInt(request.getParameter("idOrder"));
            idClient = Integer.parseInt(request.getParameter("idClient"));
            System.out.println(idClient+" "+idOrder);
            totalCost = (serviceFactory.getOrderService().findOrderByOrderId(idOrder)).getTotalCost();
            if (totalCost != 0) {
                if (serviceFactory.getAccountService().findAccountByClientId(idClient) != null) {
                    account = serviceFactory.getAccountService().findAccountByClientId(idClient);
                    if (account.getCredit() > totalCost) {
                        serviceFactory.getAccountService().payOrder(idClient, totalCost);
                        serviceFactory.getOrderService().payOrder(idOrder);
                        diagnoseSuccessfulPayment(request);
                    } else {
                        serviceFactory.getAccountService().payPartOrder(idClient);
                        serviceFactory.getOrderService().payOrder(idOrder);

                        diagnosePartPayment(request);
                    }
                } else {
                    serviceFactory.getOrderService().payOrder(idOrder);
                    diagnosePaymentByCash(request);
                }
            } else {
                diagnoseEmptyOrder(request);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish CloseOrderCommand");
        return pageName.getPath();
    }

    private static void diagnosePartPayment(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Заказ оплачен наличным и безналичным расчетом.");
        } else {
            request.getSession().setAttribute("error_data", "The order was paid by cash and by card.");
        }
    }

    private static void diagnoseEmptyOrder(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Заказ пуст.");
        } else {
            request.getSession().setAttribute("error_data", "The order is empty.");
        }
    }

    private static void diagnosePaymentByCash(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Заказ оплачен наличными и закрыт.");
        } else {
            request.getSession().setAttribute("error_data", "The order was paid by cash and closed.");
        }
    }

    private static void diagnoseSuccessfulPayment(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Заказ успешно оплачен и закрыт.");
        } else {
            request.getSession().setAttribute("error_data", "Order was successfully paid and closed.");
        }
    }



}