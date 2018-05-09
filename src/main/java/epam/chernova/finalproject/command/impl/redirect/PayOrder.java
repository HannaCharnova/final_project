package epam.chernova.finalproject.command.impl.redirect;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.entity.Account;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.ServiceException;
import epam.chernova.finalproject.factory.ServiceFactory;
import epam.chernova.finalproject.util.SessionElements;
import epam.chernova.finalproject.webenum.PageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class PayOrder implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(PayOrder.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.ORDERS;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start pay order");
        int idClient, idOrder;
        double totalCost;
        Account account;
        try {
            idOrder = Integer.parseInt(request.getParameter("idOrder"));
            totalCost = (serviceFactory.getOrderService().findOrderByOrderId(idOrder)).getTotalCost();
            idClient = ((Client) request.getSession().getAttribute("client")).getIdUser();
            if (totalCost != 0) {
                if (serviceFactory.getAccountService().findAccountByClientId(idClient) != null) {
                    account = serviceFactory.getAccountService().findAccountByClientId(idClient);
                    if (account.getCredit() > totalCost) {
                        serviceFactory.getAccountService().payOrder(idClient, totalCost);
                        serviceFactory.getOrderService().payOrder(idOrder);
                    } else {
                        diagnoseLackOfMoney(request);
                    }

                } else {
                    diagnoseNoAccount(request);
                }
            } else {
                diagnoseEmptyOrder(request);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException |
                ServiceException e)

        {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish pay order");
        return pageName.getPath();
    }

    private static void diagnoseLackOfMoney(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "На вашем счету недостаточно средств для оплаты заказа. Пополните счет либо оплатите заказ наличными.");
        } else {
            request.getSession().setAttribute("error_data", "There is no enough money on your account for paying for this order. Replenish your account or pay in cash.");
        }
    }

    private static void diagnoseEmptyOrder(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Заказ пуст.");
        } else {
            request.getSession().setAttribute("error_data", "The order is empty.");
        }
    }

    private static void diagnoseNoAccount(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "У вас нет счета. Зарегистрируйте его в личном кабинете либо оплатите заказ наличными.");
        } else {
            request.getSession().setAttribute("error_data", "You havn't got the account. Register the account in your profile or pay in cash .");
        }
    }


}