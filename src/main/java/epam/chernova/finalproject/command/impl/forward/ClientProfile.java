package epam.chernova.finalproject.command.impl.forward;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.entity.Order;
import epam.chernova.finalproject.entity.OrderProduct;
import epam.chernova.finalproject.entity.Product;
import epam.chernova.finalproject.entity.ext.Client;
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

public class ClientProfile implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(ClientProfile.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.PROFILE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: ClientProfile started.");
//        try {
//            int idClient = ((Client) request.getSession().getAttribute("client")).getIdUser();
//            List<Order> orders = serviceFactory.getOrderService().findAllOrdersByClientId(idClient);
//            List<OrderProduct> orderProducts = serviceFactory.getOrderProductService().findOrderProductsByClientId(idClient);
//            List<Product> products = serviceFactory.getProductService().findAllProducts();
//            request.setAttribute("orders", orders);
//            request.setAttribute("order_products",orderProducts);
//            request.setAttribute("products", products);
//            request.getSession().setAttribute("pageCommand", PageNameRedirect.ORDERS.getPath());
//            request.getSession().setAttribute("locale", SessionElements.getLocale(request));
//            rewrite(request);
//        } catch (ServiceException e) {
//            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
//            pageName = pageName.ERROR;
//        }
//
//        LOGGER.log(Level.INFO, "Command: ClientProfile finished.");
        return pageName.getPath();

    }

    private void rewrite(HttpServletRequest request) {
        request.setAttribute("error_data", request.getSession().getAttribute("error_data"));
        request.getSession().removeAttribute("error_data");
    }

}
