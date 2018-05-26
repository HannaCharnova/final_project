package epam.chernova.finalproject.command.impl.forward;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.entity.*;
import epam.chernova.finalproject.exception.ServiceException;
import epam.chernova.finalproject.factory.ServiceFactory;
import epam.chernova.finalproject.util.SessionElements;
import epam.chernova.finalproject.webenum.PageName;
import epam.chernova.finalproject.webenum.PageNameRedirect;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowOrderAdminCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(ShowOrderAdminCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.ORDERS;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: ShowOrderClientCommand started.");
        try {
            List<Order> orders = serviceFactory.getOrderService().findAllOrders();
            List<OrderProduct> orderProducts = serviceFactory.getOrderProductService().findAllOrderProducts();
            List<Product> products = serviceFactory.getProductService().findAllProducts();
            List<Client> clients = serviceFactory.getClientService().findAllClients();
            request.setAttribute("clients", clients);
            request.setAttribute("orders", orders);
            request.setAttribute("order_products",orderProducts);
            request.setAttribute("products", products);
            request.getSession().setAttribute("pageCommand", PageNameRedirect.ORDERS_ADMIN.getPath());
            request.getSession().setAttribute("locale", SessionElements.getLocale(request));
            rewrite(request);
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }

        LOGGER.log(Level.INFO, "Command: ShowOrderClientCommand finished.");
        return pageName.getPath();

    }

    private void rewrite(HttpServletRequest request) {
        request.setAttribute("error_data", request.getSession().getAttribute("error_data"));
        request.getSession().removeAttribute("error_data");
    }

}
