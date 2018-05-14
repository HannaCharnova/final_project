package epam.chernova.finalproject.command.impl.redirect;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.factory.ServiceFactory;
import epam.chernova.finalproject.service.AccountService;
import epam.chernova.finalproject.service.AdministratorService;
import epam.chernova.finalproject.service.ClientService;
import epam.chernova.finalproject.util.SessionElements;
import epam.chernova.finalproject.webenum.PageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddAccount implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AddAccount.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.PROFILE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start AddAccount ");
        AccountService accountService = serviceFactory.getAccountService();
        String accountNumber = request.getParameter("account-number");
        int idClient = ((Client) request.getSession().getAttribute("client")).getIdUser();
        try {
            if (accountService.findAccountByNumber(accountNumber) != null) {
                diagnoseCommonNumber(request);
            } else {
                accountService.addAccount(idClient, accountNumber);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish AddAccount");
        return pageName.getPath();
    }

    private void diagnoseCommonNumber(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Данный номер счета не принадлежит вам.");
        } else {
            request.getSession().setAttribute("error_data", "This account number doesn't belong to you.");
        }
    }

}