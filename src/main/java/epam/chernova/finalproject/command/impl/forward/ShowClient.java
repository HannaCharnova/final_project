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

public class ShowClient implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(ShowClient.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.CLIENTS;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: ShowClient started.");
        try {
            List<Client> clients = serviceFactory.getClientService().findAllClients();
            request.setAttribute("clients", clients);
            System.out.println(request.getAttribute("clients"));
            request.getSession().setAttribute("pageCommand", PageNameRedirect.CLIENTS.getPath());
            request.getSession().setAttribute("locale", SessionElements.getLocale(request));
            rewrite(request);
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }

        LOGGER.log(Level.INFO, "Command: ShowClient finished.");
        return pageName.getPath();

    }

    private void rewrite(HttpServletRequest request) {
        request.setAttribute("error_data", request.getSession().getAttribute("error_data"));
        request.getSession().removeAttribute("error_data");
    }

}
