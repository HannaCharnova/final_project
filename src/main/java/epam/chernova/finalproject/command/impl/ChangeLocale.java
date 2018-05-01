package epam.chernova.finalproject.command.impl;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.webenum.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLocale implements ICommand{
    private PageName pageName = PageName.INDEX;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String locale = (String) request.getSession().getAttribute("locale");
        if (locale == "ru") {
            request.getSession().setAttribute("locale", "en");
        } else {
            request.getSession().setAttribute("locale", "ru");
        }
        return pageName.getPath();
    }
}
