package epam.chernova.finalproject.entity;

import java.util.Objects;

public class Order {
    private int idOrder;
    private String date;
    private double totalCost;
    private int idClient;
    private boolean status;

    public Order() {
    }

    public Order(String date, double totalCost, int idClient, boolean status) {
        this.date = date;
        this.totalCost = totalCost;
        this.idClient = idClient;
        this.status = status;
    }

    public Order(int idOrder, String date, double totalCost, int idClient, boolean status) {
        this.idOrder = idOrder;
        this.date = date;
        this.totalCost = totalCost;
        this.idClient = idClient;
        this.status = status;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return idOrder == order.idOrder &&
                Double.compare(order.totalCost, totalCost) == 0 &&
                idClient == order.idClient &&
                status == order.status &&
                Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idOrder, date, totalCost, idClient, status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", date='" + date + '\'' +
                ", totalCost=" + totalCost +
                ", idClient=" + idClient +
                ", status=" + status +
                '}';
    }
}
