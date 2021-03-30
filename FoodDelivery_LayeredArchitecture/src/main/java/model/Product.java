package model;

public class Product {

    private int product_id;
    private String name;
    private double price;
    private int deleted;

    public Product() {

    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.deleted = 0;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
