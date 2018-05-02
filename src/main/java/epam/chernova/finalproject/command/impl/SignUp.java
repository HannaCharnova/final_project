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
        System.out.println(name+ surname+ login+ password+ email);
        try{
        Client client = clientService.signUp(login, password, name,surname,email);
        if (client == null) {
            if(clientService.findClientByLogin(login)!=null) {
                diagnoseCommonLogin(request);
            }else{
                diagnoseCommonEmail(request);
            }
        } else {
            LOGGER.log(Level.INFO, "Successful sign up in account as " + client.getLogin());
        }
        response.sendRedirect(PageNameRedirect.INDEX.getPath());
    } catch (Exception e) {
            e.printStackTrace();
        LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
        pageName = pageName.ERROR;
    }
        LOGGER.log(Level.INFO, "Command: Sign Up finished.");
        return pageName.getPath();
    }


    private void diagnoseCommonLogin(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Пользователь с таким логином уже сужествует.");
        } else {
            request.getSession().setAttribute("error_data", "User with such login is already exists.");
        }
    }


    private void diagnoseCommonEmail(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Пользователь с таким адресом электронной почты уже сужествует.");
        } else {
            request.getSession().setAttribute("error_data", "User with such email is already exists.");
        }
    }


}
