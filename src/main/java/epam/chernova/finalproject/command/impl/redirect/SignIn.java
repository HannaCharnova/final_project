package epam.chernova.finalproject.command.impl.redirect;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.entity.ext.Administrator;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.ServiceException;
import epam.chernova.finalproject.factory.ServiceFactory;
import epam.chernova.finalproject.webenum.PageName;
import epam.chernova.finalproject.webenum.PageNameRedirect;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignIn.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: Sign in started.");
        String login = request.getParameter("login_in");
        String password = request.getParameter("password_in");
        boolean role;
        if (request.getParameter("check")!=null) {
            role = true;
        } else {
            role = false;
        }
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            diagnoseIncorrectSignIn(request);
            return pageName.getPath();
        }

        try {
            if (role) {
                Administrator administrator = serviceFactory.getAdministratorService().signIn(login, password);
                if (administrator != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("role", 1);
                    session.setAttribute("admin", administrator);
                    LOGGER.log(Level.INFO, "Successful sign in account as administrator " + administrator.getLogin());
                    response.sendRedirect(PageNameRedirect.INDEX.getPath());
                } else {
                    diagnoseIncorrectSignIn(request);
                    return pageName.getPath();
                }
            } else {
                Client client = serviceFactory.getClientService().signIn(login, password);
                if (client != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("role", 2);
                    session.setAttribute("client", client);
                    LOGGER.log(Level.INFO, "Successful sign in account as client " + client.getLogin());
                    response.sendRedirect(PageNameRedirect.INDEX.getPath());
                } else {
                    diagnoseIncorrectSignIn(request);
                    return pageName.getPath();
                }
            }
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Sign in finished.");
        return pageName.getPath();
    }

    private static void diagnoseIncorrectSignIn(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Неверно введен логин или пароль. Повторите вход.");
        } else {
            request.getSession().setAttribute("error_data", "Incorrect login or password. Try again.");
        }
    }
}
