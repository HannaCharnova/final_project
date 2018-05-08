package epam.chernova.finalproject.entity;

import java.util.Objects;

public class OrderProduct {
    private int idOrder;
    private int idProduct;
    private int quantity;

    public OrderProduct() {
    }

    public OrderProduct(int idOrder, int idProduct, int quantity) {
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
        return idOrder == that.idOrder &&
                idProduct == that.idProduct &&
                quantity == that.quantity;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idOrder, idProduct, quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "idOrder=" + idOrder +
                ", idProduct=" + idProduct +
                ", quantity=" + quantity +
                '}';
    }
}
