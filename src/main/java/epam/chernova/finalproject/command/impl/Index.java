package epam.chernova.finalproject.command.impl;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.webenum.PageName;
import epam.chernova.finalproject.webenum.PageNameRedirect;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class Index implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(ChangeLocale.class);
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Index started.");
        String locale = (String) request.getSession().getAttribute("locale");
        if (locale == null || locale.isEmpty()) {
            locale = Locale.getDefault().getLanguage();
        }
        request.getSession().setAttribute("pageCommand", PageNameRedirect.INDEX.getPath());
        request.getSession().setAttribute("locale",locale);
        System.out.println(request.getSession().getAttribute("role"));
        LOGGER.log(Level.INFO, "Command:Index finished.");
        return pageName.getPath();
    }
}
