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


public class DeleteAdminCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(DeleteAdminCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start DeleteAdminCommand");
        int idAdmin;
        try {
            idAdmin = Integer.parseInt(request.getParameter("idAdmin"));
            serviceFactory.getAdministratorService().deleteAdministrator(idAdmin);
            diagnoseDeleteAdmin(request);
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException |
                ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish DeleteAdminCommand");
        return pageName.getPath();
    }

    private static void diagnoseDeleteAdmin(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Администратор удален.");
        } else {
            request.getSession().setAttribute("error_data", "Administrator has been deleted.");
        }
    }

}