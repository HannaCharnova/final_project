package epam.chernova.finalproject.command.impl.redirect;

import epam.chernova.finalproject.command.ICommand;
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


public class RemoveBasketProduct implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(RemoveBasketProduct.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start remove product from basket");
        int idOrder,idProduct,quantity;
        double deltaTotalCost;
        try {
            System.out.println(request.getParameter("idOrder")+" "+request.getParameter("idProduct")+" "+request.getParameter("quantity"));
            idOrder = Integer.parseInt(request.getParameter("idOrder"));
            idProduct = Integer.parseInt(request.getParameter("idProduct"));
            quantity = Integer.parseInt(request.getParameter("quantity"));
            serviceFactory.getOrderProductService().removeOrderProduct(idOrder,idProduct);
            deltaTotalCost = (serviceFactory.getProductService().findProductById(idProduct)).getCost() * quantity;
            serviceFactory.getOrderService().editOrderCost(idOrder, -deltaTotalCost);

            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish remove product from basket");
        return pageName.getPath();
    }
}