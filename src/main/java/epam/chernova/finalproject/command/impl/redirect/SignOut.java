package epam.chernova.finalproject.command.impl.redirect;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.util.SessionElements;
import epam.chernova.finalproject.webenum.PageName;
import epam.chernova.finalproject.webenum.PageNameRedirect;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SignOut implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(SignOut.class);
    private PageName jspPageName = PageName.ERROR;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Sign out start");
        try {
            String locale = SessionElements.getLocale(request);
            if ((int)request.getSession().getAttribute("role") == 1) {
                request.getSession().removeAttribute("admin");
                request.getSession().setAttribute("role",0);
            }else{
                request.getSession().removeAttribute("client");
                request.getSession().setAttribute("role",0);
            }
            request.getSession().invalidate();
            request.getSession().setAttribute("locale", locale);
            response.sendRedirect(PageNameRedirect.INDEX.getPath());
        } catch (IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Sign out finish");
        return jspPageName.getPath();
    }
}
