package epam.chernova.finalproject.command.impl.forward;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.entity.Product;
import epam.chernova.finalproject.exception.ServiceException;
import epam.chernova.finalproject.factory.ServiceFactory;
import epam.chernova.finalproject.webenum.PageName;
import epam.chernova.finalproject.webenum.PageNameRedirect;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class FindByType implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(FindByType.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.PRODUCTS;
    private String productType;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: FindByType started.");
        try {
            String type = request.getParameter("product_type");
            if (type != null) {
                productType = type;
            }
            List<Product> products = serviceFactory.getProductService().findProductByType(productType);
            request.setAttribute("products", products);
            request.getSession().setAttribute("pageCommand", PageNameRedirect.FIND_BY_TYPE.getPath());
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }

        LOGGER.log(Level.INFO, "Command: FindByType finished.");
        return pageName.getPath();
    }


}
