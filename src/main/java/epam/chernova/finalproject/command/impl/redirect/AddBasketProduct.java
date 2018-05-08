package epam.chernova.finalproject.command.impl.redirect;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.factory.ServiceFactory;
import epam.chernova.finalproject.webenum.PageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class AddBasketProduct implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AddBasketProduct.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName jspPageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start add product to basket");
        int idClient;
        try {
            idClient = ((Client) request.getSession().getAttribute("client")).getIdUser();
            IOrderDao orderDao = new OrderDAO();

            int number = 0;

            for (Product product : allProducts) {
                if (request.getParameter(AttributeParameterName.PRODUCT_ID.getValue() + "_" + product.getId()) != null &&
                        request.getParameter(AttributeParameterName.NUMBER_FOR_ADD.getValue() + "_" + product.getId()) != null) {

                    productId = Integer.valueOf(request.getParameter(AttributeParameterName.PRODUCT_ID.getValue() + "_" + product.getId()));
                    productCount = Integer.valueOf(request.getParameter(AttributeParameterName.NUMBER_FOR_ADD.getValue() + "_" + product.getId()));

                    Double orderCost = productService.getProductById(productId).getCost();
                    Integer orderId = orderDao.getOrderIdByClientId(clientId);
                    if (productCount.equals(0)) {
                        number++;
                    } else {
                        if (orderProductDao.findOrderProduct(productId, orderId)) {
                            orderProductDao.editOrderProduct(productId, productCount, orderId);
                            orderService.editOrder(clientId, orderCost, productCount);
                        } else {
                            orderProductService.addOrderProduct(clientId, productId, productCount);
                            orderService.editOrder(clientId, orderCost, productCount);
                        }
                    }
                } else {
                    number++;
                }
            }
            if (number == allProducts.size()) {
                diagnoseError(request);
            }
            response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
        } catch (ServiceException | IOException | DaoException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Finish add product to basket");
        return jspPageName.getPath();
    }

    /**
     * @param request
     */
    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Ничего не выбрано");
        } else {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "You hadn't choose anything to add");
        }
    }
}