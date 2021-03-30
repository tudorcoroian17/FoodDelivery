package model;

import java.util.Date;

public class Order {

    private int order_id;
    private int client_id;
    private double total;
    private int deleted;
    private String dateBuy;
    private String address;
    private int payment;        // 0 => cash
                                // 1 => card

    public Order () {

    }

    public Order(int client_id, double total, String address, int payment, String date) {
        this.client_id = client_id;
        this.total = total;
        this.address = address;
        this.payment = payment;
        this.dateBuy = date;
        this.deleted = 0;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(String date) {
        this.dateBuy = date;
    }
}
