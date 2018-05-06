package epam.chernova.finalproject.entity;

import java.util.Objects;

public class Product {
    private int idProduct;
    private String nameEn;
    private String nameRu;
    private double cost;
    private String type;
    private double weight;
    private String imagePath;

    public Product() {
    }

    public Product(int idProduct, String nameEn, String nameRu,double cost, String type, double weight, String imagePath) {
        this.idProduct = idProduct;
        this.nameEn = nameEn;
        this.nameRu = nameRu;
        this.cost = cost;
        this.type = type;
        this.weight = weight;
        this.imagePath = imagePath;
    }

    public Product(String nameEn, String nameRu, double cost, String type, double weight, String imagePath) {
        this.nameEn = nameEn;
        this.nameRu = nameRu;
        this.cost = cost;
        this.type = type;
        this.weight = weight;
        this.imagePath = imagePath;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getnameEn() {
        return nameEn;
    }

    public void setnameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getnameRu() {
        return nameRu;
    }

    public void setnameRu(String nameRu) {
        this.nameRu = nameRu;
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
                Objects.equals(nameEn, product.nameEn) &&
                Objects.equals(nameRu, product.nameRu) &&
                Objects.equals(type, product.type) &&
                Objects.equals(imagePath, product.imagePath);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idProduct, nameEn, nameRu, cost, type, weight, imagePath);
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", nameEn='" + nameEn + '\'' +
                ", nameRu='" + nameRu + '\'' +
                ", cost=" + cost +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
