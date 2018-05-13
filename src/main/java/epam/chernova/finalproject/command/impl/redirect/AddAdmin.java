package epam.chernova.finalproject.command.impl.redirect;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.entity.Product;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.factory.ServiceFactory;
import epam.chernova.finalproject.service.AdministratorService;
import epam.chernova.finalproject.service.ClientService;
import epam.chernova.finalproject.service.ProductService;
import epam.chernova.finalproject.util.SessionElements;
import epam.chernova.finalproject.webenum.PageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddAdmin implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AddAdmin.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start add admin ");
        ClientService clientService = serviceFactory.getClientService();
        AdministratorService administratorService = serviceFactory.getAdministratorService();
        String login = request.getParameter("login-admin");
        String password = request.getParameter("password-admin");
        System.out.println(login+password);
        try {
            if (clientService.findClientByLogin(login) != null || administratorService.findAdministratorByLogin(login) != null) {
                diagnoseCommonLogin(request);
            } else {
                administratorService.addAdministrator(login,password);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish add admin");
        return pageName.getPath();
    }

    private void diagnoseCommonLogin(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Пользователь с таким логином уже сужествует.");
        } else {
            request.getSession().setAttribute("error_data", "User with such login is already exists.");
        }
    }

}