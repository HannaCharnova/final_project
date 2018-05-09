package epam.chernova.finalproject.command.impl.forward;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.entity.Order;
import epam.chernova.finalproject.entity.OrderProduct;
import epam.chernova.finalproject.entity.Product;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.ServiceException;
import epam.chernova.finalproject.factory.ServiceFactory;
import epam.chernova.finalproject.service.OrderService;
import epam.chernova.finalproject.util.SessionElements;
import epam.chernova.finalproject.webenum.PageName;
import epam.chernova.finalproject.webenum.PageNameRedirect;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowOrderClient implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(ShowOrderClient.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.ORDERS;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: ShowOrderClient started.");
        try {
            int idClient = ((Client) request.getSession().getAttribute("client")).getIdUser();
            List<Order> orders = serviceFactory.getOrderService().findAllOrdersByClientId(idClient);
            List<OrderProduct> orderProducts = serviceFactory.getOrderProductService().findOrderProductsByClientId(idClient);
            request.setAttribute("orders", orders);
            request.getSession().setAttribute("pageCommand", PageNameRedirect.ORDERS.getPath());
            request.getSession().setAttribute("locale", SessionElements.getLocale(request));
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }

        LOGGER.log(Level.INFO, "Command: ShowOrderClient finished.");
        return pageName.getPath();

    }
}
