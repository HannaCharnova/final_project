package epam.chernova.finalproject.command.impl.redirect;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.entity.Client;
import epam.chernova.finalproject.factory.ServiceFactory;
import epam.chernova.finalproject.service.AccountService;
import epam.chernova.finalproject.service.ReviewService;
import epam.chernova.finalproject.util.SessionElements;
import epam.chernova.finalproject.webenum.PageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddReviewCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AddReviewCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.ORDERS;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start AddReviewCommand ");
        ReviewService reviewService = serviceFactory.getReviewService();
        String reviewText = request.getParameter("review");
        int idClient = ((Client) request.getSession().getAttribute("client")).getIdUser();
        int mark = diagnoseClientMark(request);
        try {
            reviewService.addReview(idClient, reviewText, mark);
            diagnoseAddReview(request);
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish AddReviewCommand");
        return pageName.getPath();
    }

    private int diagnoseClientMark(HttpServletRequest request) {
        if (request.getParameter("rating5") != null) {
            return 5;
        } else {
            if (request.getParameter("rating4") != null) {
                return 4;
            } else {
                if (request.getParameter("rating3") != null) {
                    return 3;
                } else {
                    if (request.getParameter("rating2") != null) {
                        return 2;
                    } else {
                        if (request.getParameter("rating1") != null) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
            }
        }
    }

    private static void diagnoseAddReview(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Благодарим за отзыв!");
        } else {
            request.getSession().setAttribute("error_data", "Thanks for your feedback!");
        }
    }

}