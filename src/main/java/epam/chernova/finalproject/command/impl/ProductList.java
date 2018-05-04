package epam.chernova.finalproject.command.impl;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.entity.Product;
import epam.chernova.finalproject.exception.ServiceException;
import epam.chernova.finalproject.factory.ServiceFactory;
import epam.chernova.finalproject.service.impl.ProductServiceImpl;
import epam.chernova.finalproject.webenum.PageName;
import epam.chernova.finalproject.webenum.PageNameRedirect;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ProductList implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(ProductList.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.MENU;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: Product list started.");
        try {
            List<Product> products = serviceFactory.getProductService().findAllProducts();
            System.out.println(products);
            request.setAttribute("products", products);
            request.getSession().setAttribute("pageCommand", PageNameRedirect.MENU.getPath());
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }

        LOGGER.log(Level.INFO, "Command: Product list finished.");
        return pageName.getPath();
    }
}
