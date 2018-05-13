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


public class BanClient implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(BanClient.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start BanClient");
        int idClient;
        try {
            idClient = Integer.parseInt(request.getParameter("idClient"));
            if ((serviceFactory.getClientService().findClientById(idClient)).isBan()) {
                serviceFactory.getClientService().unbanClient(idClient);
                diagnoseClientUnban(request);
            } else {
                serviceFactory.getClientService().banClient(idClient);
                diagnoseClientBan(request);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException |
                ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish BanClient");
        return pageName.getPath();
    }

    private static void diagnoseClientBan(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Клиент заблокирован.");
        } else {
            request.getSession().setAttribute("error_data", "Client blocked.");
        }
    }

    private static void diagnoseClientUnban(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Клиент разблокирован.");
        } else {
            request.getSession().setAttribute("error_data", "Client unblocked.");
        }
    }


}