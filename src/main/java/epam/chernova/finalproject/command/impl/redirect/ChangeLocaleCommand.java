package epam.chernova.finalproject.command.impl.redirect;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.util.SessionElements;
import epam.chernova.finalproject.webenum.PageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocaleCommand implements ICommand{
    private static final Logger LOGGER = Logger.getLogger(ChangeLocaleCommand.class);
    private PageName pageName = PageName.INDEX;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Change Locale started.");
        try {
            String locale = (String) request.getSession().getAttribute("locale");
            if (locale == "ru") {
                request.getSession().setAttribute("locale", "en");
            } else {
                request.getSession().setAttribute("locale", "ru");
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Change Locale finished.");
        return pageName.getPath();
    }
}
