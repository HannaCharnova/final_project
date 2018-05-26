package epam.chernova.finalproject.command.impl.redirect;

import epam.chernova.finalproject.command.ICommand;
import epam.chernova.finalproject.entity.Client;
import epam.chernova.finalproject.exception.ServiceException;
import epam.chernova.finalproject.factory.ServiceFactory;
import epam.chernova.finalproject.util.SessionElements;
import epam.chernova.finalproject.webenum.PageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddBasketProductCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AddBasketProductCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start add product to basket");
        int idClient, idOrder, idProduct, quantity;
        double deltaTotalCost;
        try {
            idClient = ((Client) request.getSession().getAttribute("client")).getIdUser();
            if (serviceFactory.getOrderService().findActiveOrderByClientId(idClient) != null) {
                idOrder = serviceFactory.getOrderService().findActiveOrderByClientId(idClient).getIdOrder();
                idProduct = Integer.parseInt(request.getParameter("idProduct"));
                quantity = Integer.parseInt(request.getParameter("quantity"));
                if (quantity > 0) {
                    if (serviceFactory.getOrderProductService().checkProductInActiveOrder(idOrder, idProduct) == null) {
                        serviceFactory.getOrderProductService().addOrderProduct(idOrder, idProduct, quantity);
                        deltaTotalCost = (serviceFactory.getProductService().findProductById(idProduct)).getCost() * quantity;
                        serviceFactory.getOrderService().editOrderCost(idOrder, deltaTotalCost);
                        diagnoseAddProduct(request);
                    } else {
                        serviceFactory.getOrderProductService().addOrderProductQuantity(idOrder, idProduct, quantity);
                        deltaTotalCost = (serviceFactory.getProductService().findProductById(idProduct)).getCost() * quantity;
                        serviceFactory.getOrderService().editOrderCost(idOrder, deltaTotalCost);
                        diagnoseAddProduct(request);
                    }
                } else {
                    diagnoseEmptyChoise(request);
                }
            } else {
                serviceFactory.getOrderService().addOrder(idClient);
                idOrder = serviceFactory.getOrderService().findActiveOrderByClientId(idClient).getIdOrder();
                idProduct = Integer.parseInt(request.getParameter("idProduct"));
                quantity = Integer.parseInt(request.getParameter("quantity"));
                if (quantity > 0) {

                    serviceFactory.getOrderProductService().addOrderProduct(idOrder, idProduct, quantity);
                    deltaTotalCost = (serviceFactory.getProductService().findProductById(idProduct)).getCost() * quantity;
                    serviceFactory.getOrderService().editOrderCost(idOrder, deltaTotalCost);
                    diagnoseAddProduct(request);
                } else {
                    diagnoseEmptyChoise(request);
                }
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish add product to basket");
        return pageName.getPath();
    }

    private static void diagnoseAddProduct(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Продукт добавлен в ваш заказ.");
        } else {
            request.getSession().setAttribute("error_data", "Product was added to your order.");
        }
    }

    private static void diagnoseEmptyChoise(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Выберите необходимое количество продукта.");
        } else {
            request.getSession().setAttribute("error_data", "Choose the quantity of this product.");
        }
    }


}