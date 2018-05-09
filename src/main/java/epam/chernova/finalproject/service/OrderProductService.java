package epam.chernova.finalproject.service;


import epam.chernova.finalproject.entity.OrderProduct;
import epam.chernova.finalproject.exception.ServiceException;

import java.util.List;

public interface OrderProductService {
    void addOrderProduct(int idOrder,int idProduct,int quantity) throws ServiceException;

    List<OrderProduct> findOrderProductsByClientId(int idClient);
}
