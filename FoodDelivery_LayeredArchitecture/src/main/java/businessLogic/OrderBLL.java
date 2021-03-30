package businessLogic;

import access.OrderAccess;
import model.Order;

import java.util.Date;
import java.util.List;

public class OrderBLL {

    private OrderAccess orderAccess;

    public OrderBLL() {
        orderAccess = new OrderAccess();
    }

    public List<Order> findOrderByDate(String date) {
        List<Order> orders = orderAccess.findOrderByDate(date);
        return orders;
    }

    public List<Order> findOrderByClientId(int id) {
        List<Order> orders = orderAccess.findOrderByClientId(id);
        return orders;
    }

    public Order findOrderById(int id) {
        Order order = orderAccess.findOrderById(id).get(0);
        return order;
    }

    public List<Order> findAllOrders() {
        List<Order> orders = orderAccess.findAllOrders();
        return orders;
    }

    public void insertOrder(Order order) {
        orderAccess.insert(order);
    }

    public void updateOrder(Order order) {
        Order orderOld = findOrderById(order.getOrder_id());
        orderAccess.update(orderOld, order);
    }

    public void deleteOrder(Order order) {
        orderAccess.delete(order);
    }

    public void deleteOrderById(int id) {
        orderAccess.deleteById(id);
    }
}
