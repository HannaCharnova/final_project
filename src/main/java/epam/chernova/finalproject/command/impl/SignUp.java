package epam.chernova.finalproject.command.impl;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.factory.ServiceFactory;
import epam.chernova.finalproject.service.ClientService;
import epam.chernova.finalproject.webenum.PageName;
import epam.chernova.finalproject.webenum.PageNameRedirect;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignUp implements ICommand{
    private static final Logger LOGGER = Logger.getLogger(SignUp.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: Sign up started.");
        ClientService clientService = serviceFactory.getClientService();
        String login =  request.getParameter("login_up");
        String password = request.getParameter("password_up");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        try{
        Client client = clientService.signUp(name, surname, login, password, email);
        if (client == null) {
            if(clientService.findClientByLogin(login)) {
                diagnoseCommonLogin(request);
            }else{
                diagnoseCommonEmail(request);
            }
        } else {
            LOGGER.log(Level.INFO, "Successful sign up in account as " + client.getLogin());
        }
        response.sendRedirect(PageNameRedirect.INDEX.getPath());
    } catch (Exception e) {
        LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
        pageName = pageName.ERROR;
    }
        LOGGER.log(Level.INFO, "Command: Sign Up finished.");
        return pageName.getPath();
    }


    private void diagnoseCommonLogin(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Пользовател с таким логином уже сужествует");
        } else {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "User with such login already exists");
        }
    }


    private void diagnoseCommonEmail(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Пользовател с такой почтой уже существует");
        } else {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "User with such mail already exists");
        }
    }


}
