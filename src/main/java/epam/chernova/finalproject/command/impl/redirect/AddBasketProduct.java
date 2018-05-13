package epam.chernova.finalproject.command.impl.redirect;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.DaoException;
import epam.chernova.finalproject.exception.ServiceException;
import epam.chernova.finalproject.factory.ServiceFactory;
import epam.chernova.finalproject.util.SessionElements;
import epam.chernova.finalproject.webenum.PageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class AddBasketProduct implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AddBasketProduct.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start add product to basket");
        int idClient,idOrder,idProduct,quantity;
        double deltaTotalCost;
        try {
            idClient = ((Client) request.getSession().getAttribute("client")).getIdUser();
            if (serviceFactory.getOrderService().findActiveOrderByClientId(idClient) != null) {
                System.out.println("add to old order");
                idOrder = serviceFactory.getOrderService().findActiveOrderByClientId(idClient).getIdOrder();
                idProduct = Integer.parseInt(request.getParameter("idProduct"));
                quantity = Integer.parseInt(request.getParameter("quantity"));
                serviceFactory.getOrderProductService().addOrderProduct(idOrder,idProduct,quantity);
                deltaTotalCost=(serviceFactory.getProductService().findProductById(idProduct)).getCost()*quantity;
                serviceFactory.getOrderService().editOrderCost(idOrder,deltaTotalCost);
            } else {
                serviceFactory.getOrderService().addOrder(idClient);
                idOrder = serviceFactory.getOrderService().findActiveOrderByClientId(idClient).getIdOrder();
                idProduct = Integer.parseInt(request.getParameter("idProduct"));
                quantity = Integer.parseInt(request.getParameter("quantity"));
                serviceFactory.getOrderProductService().addOrderProduct(idOrder,idProduct,quantity);
                deltaTotalCost=(serviceFactory.getProductService().findProductById(idProduct)).getCost()*quantity;
                serviceFactory.getOrderService().editOrderCost(idOrder,deltaTotalCost);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException|ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish add product to basket");
        return pageName.getPath();
    }
}