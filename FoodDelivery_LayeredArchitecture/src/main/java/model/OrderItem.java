package model;

public class OrderItem {

    private int order_item_id;
    private int order_id;
    private String product_name;
    private int quantity;
    private int deleted;

    public OrderItem() {

    }

    public OrderItem(int order_id, String product_name, int quantity) {
        this.order_id = order_id;
        this.product_name = product_name;
        this.quantity = quantity;
        this.deleted = 0;
    }

    public int getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(int order_item_id) {
        this.order_item_id = order_item_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
