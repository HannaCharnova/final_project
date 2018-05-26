package epam.chernova.finalproject.command.impl.redirect;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.exception.ServiceException;
import epam.chernova.finalproject.factory.ServiceFactory;
import epam.chernova.finalproject.util.SessionElements;
import epam.chernova.finalproject.webenum.PageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteProductCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(DeleteProductCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start delete product");
        int idProduct;
        try {
            idProduct = Integer.parseInt(request.getParameter("idProduct"));
            if (serviceFactory.getOrderProductService().checkActiveOrderProduct(idProduct)) {
                diagnoseDeleteError(request);
            } else {
                serviceFactory.getProductService().deleteProduct(idProduct);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException |
                ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish delete product");
        return pageName.getPath();
    }

    private static void diagnoseDeleteError(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Данный продукт присутствует в открытом заказе одного из клиентов. Удаление невозможно.");
        } else {
            request.getSession().setAttribute("error_data", "This product exist in the active client's order. It's impossible to delete this product now.");
        }
    }

}