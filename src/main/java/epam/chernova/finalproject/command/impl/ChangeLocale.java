package epam.chernova.finalproject.command.impl;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.factory.ServiceFactory;
import epam.chernova.finalproject.webenum.PageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLocale implements ICommand{
    private static final Logger LOGGER = Logger.getLogger(ChangeLocale.class);
    private PageName pageName = PageName.INDEX;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Change Locale started.");
        String locale = (String) request.getSession().getAttribute("locale");
        if (locale == "ru") {
            request.getSession().setAttribute("locale", "en");
        } else {
            request.getSession().setAttribute("locale", "ru");
        }
        LOGGER.log(Level.INFO, "Command:Change Locale finished.");
        return pageName.getPath();
    }
}
