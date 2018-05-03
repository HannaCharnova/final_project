package epam.chernova.finalproject.command.impl;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.exception.ServiceException;
import epam.chernova.finalproject.factory.ServiceFactory;
import epam.chernova.finalproject.webenum.PageName;
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
            setMenu(request);
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command: Product list started.");
        return pageName.getPath();
    }

    private void setMenu(HttpServletRequest request) {
        List<Product> menu = new ArrayList<>();
        List<Stock> stocks = serviceFactory.getStockService().getAllStocks();
        for (Stock stock : stocks) {
            allProducts.add(serviceFactory.getProducteService().getProductById(stock.getProductId()));
        }
        Common.calculatePageNumber(request, allProducts);
    }
}
