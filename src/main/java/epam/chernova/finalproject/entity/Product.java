package epam.chernova.finalproject.entity;

import java.util.Objects;

public class Product {
    private int idProduct;
    private String name;
    private double cost;
    private String type;
    private double weight;
    private String description;
    private String imagePath;

    public Product() {
    }

    public Product(int idProduct, String name, double cost, String type, double weight, String description, String imagePath) {
        this.idProduct = idProduct;
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.weight = weight;
        this.description = description;
        this.imagePath = imagePath;
    }

    public Product(String name, double cost, String type, double weight, String description, String imagePath) {
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.weight = weight;
        this.description = description;
        this.imagePath = imagePath;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return idProduct == product.idProduct &&
                Double.compare(product.cost, cost) == 0 &&
                Double.compare(product.weight, weight) == 0 &&
                Objects.equals(name, product.name) &&
                Objects.equals(type, product.type) &&
                Objects.equals(description, product.description) &&
                Objects.equals(imagePath, product.imagePath);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idProduct, name, cost, type, weight, description, imagePath);
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
