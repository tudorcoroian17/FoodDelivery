package businessLogic;

import access.OrderItemAccess;
import businessLogic.validators.QuantityValidator;
import businessLogic.validators.Validator;
import model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderItemBLL {

    private List<Validator<OrderItem>> validators;
    private OrderItemAccess orderItemAccess;

    public OrderItemBLL() {
        validators = new ArrayList<Validator<OrderItem>>();
        validators.add(new QuantityValidator());
        orderItemAccess = new OrderItemAccess();
    }

    public List<OrderItem> findOrderItemByOrderId(int id) {
        List<OrderItem> orderItems = orderItemAccess.findOrderItemByOrderId(id);
        return orderItems;
    }

    public List<OrderItem> findOrderItemByProductName(String name) {
        List<OrderItem> orderItems = orderItemAccess.findOrderItemByProductName(name);
        return orderItems;
    }

    public OrderItem findOrderItemById(int id) {
        OrderItem orderItem = orderItemAccess.findOrderItemById(id).get(0);
        return orderItem;
    }

    public List<OrderItem> findAllOrderItems() {
        List<OrderItem> orderItems = orderItemAccess.findAllOrderItems();
        return orderItems;
    }

    public void insertOrderItem(OrderItem orderItem) {
        for (Validator<OrderItem> validator : validators) {
            validator.validate(orderItem);
        }
        orderItemAccess.insert(orderItem);
    }

    public void updateOrderItem(OrderItem orderItem) {
        for (Validator<OrderItem> validator : validators) {
            validator.validate(orderItem);
        }
        OrderItem orderItemOld = findOrderItemById(orderItem.getOrder_item_id());
        orderItemAccess.update(orderItemOld, orderItem);
    }

    public void deleteOrderItem (OrderItem orderItem) {
        orderItemAccess.delete(orderItem);
    }

    public void deleteOrderItemById (int id) {
        orderItemAccess.deleteById(id);
    }


}
