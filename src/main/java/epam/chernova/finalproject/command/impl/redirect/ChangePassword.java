package epam.chernova.finalproject.command.impl.redirect;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.entity.ext.Administrator;
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


public class ChangePassword implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(ChangePassword.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start ChangePassword");
        String newPassword = (String) request.getParameter("password-new");
        String oldPassword = (String) request.getParameter("password-old");
        System.out.println(oldPassword);
        try {
            if ((int) request.getSession().getAttribute("role") == 2) {
                int idClient = ((Client) request.getSession().getAttribute("client")).getIdUser();
                if (serviceFactory.getClientService().findClientByIdAndPassword(idClient, oldPassword) != null) {
                    Client client = serviceFactory.getClientService().changePassword(idClient, newPassword);
                    request.getSession().setAttribute("client", client);
                    diagnoseChangePassword(request);
                } else {
                    diagnoseWrongOldPassword(request);
                }
            } else {
                int idAdmin = ((Administrator) request.getSession().getAttribute("admin")).getIdUser();
                if (serviceFactory.getAdministratorService().findAdministratorByIdAndPassword(idAdmin, oldPassword) != null) {
                    Administrator administrator = serviceFactory.getAdministratorService().changePassword(idAdmin, oldPassword);
                    request.getSession().setAttribute("admin", administrator);
                    diagnoseChangePassword(request);
                } else {
                    diagnoseWrongOldPassword(request);
                }
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException |
                ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish ChangePassword");
        return pageName.getPath();
    }

    private static void diagnoseChangePassword(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Пароль изменен.");
        } else {
            request.getSession().setAttribute("error_data", "Password changed.");
        }
    }

    private static void diagnoseWrongOldPassword(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Старый пароль введен неверно.");
        } else {
            request.getSession().setAttribute("error_data", "Old password is wrong.");
        }
    }

}