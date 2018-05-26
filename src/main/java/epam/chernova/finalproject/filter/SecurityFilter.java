package epam.chernova.finalproject.filter;

import epam.chernova.finalproject.command.CommandName;
import epam.chernova.finalproject.webenum.PageName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter",
        urlPatterns = {"/epam.by/*"})
public class SecurityFilter implements Filter {

    public void init(FilterConfig fConfig) {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            String uri = ((HttpServletRequest) request).getRequestURI();
            String command;
            if (uri.contains(".jpg")) {
                return;
            } else {
                command = uri.replace("/epam.by/", "");
            }
            CommandName commandName = CommandName.valueOf(command.toUpperCase());
            String commandRole;
            if (commandName == null) {
                commandRole = "none";
            } else {
                commandRole = commandName.getRole();
            }
            if (!commandRole.equals("none")) {
                Integer sessionRole = (Integer) ((HttpServletRequest) request).getSession().getAttribute("role");
                String role;
                if (sessionRole == null || sessionRole == 0) {
                    role = "all";
                } else {
                    if (sessionRole == 1) {
                        role = "admin";
                    } else {
                        role = "client";
                    }
                }
                if (!role.equals(commandRole) && !commandRole.equals("all")) {
                    ((HttpServletResponse) response).sendRedirect("/epam.by/index");
                    return;
                }
            }
        } catch (IllegalArgumentException e) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(PageName.ERROR.getPath());
            dispatcher.forward(req, resp);
            return;
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
