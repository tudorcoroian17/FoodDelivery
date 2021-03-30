package presentation;

import businessLogic.OrderBLL;
import businessLogic.OrderItemBLL;
import businessLogic.ProductBLL;
import businessLogic.UserBLL;
import model.Order;
import model.OrderItem;
import model.Product;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.NoSuchElementException;

public class CustomerGUI {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton showProductsButton;
    private JScrollPane product;
    private JTable table1;
    private JTabbedPane tabbedPane2;
    private JPanel deleteProd;
    private JButton addToCartButton;
    private JTextField textField6;
    private JButton updateButton;
    private JTabbedPane tabbedPane3;
    private JScrollPane all;
    private JTable table3;
    private JButton showCartButton;
    private JScrollPane byDate;
    private JTable table4;
    private JButton showOrdersByDateButton;
    private JTextField textField1;
    private JButton startOrderButton;
    private JTextField textField2;
    private JRadioButton cardRadioButton;
    private JRadioButton cashRadioButton;
    private JButton submitOrderButton;
    private JTextField textField3;
    private JTextField text1;
    private JPasswordField pass1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;
    private JTextField textField4;
    private JTextField textField5;
    private JButton showMyOrdersButton;

    private User user;
    private UserBLL userBLL;
    private ProductBLL productBLL;
    private OrderBLL orderBLL;
    private OrderItemBLL orderItemBLL;
    private Order order;

    private int pay = 0;

    public CustomerGUI(User user) {
        this.user = user;
        userBLL = new UserBLL();
        productBLL = new ProductBLL();
        orderBLL = new OrderBLL();
        orderItemBLL = new OrderItemBLL();
        JFrame frame = new JFrame("CustomerGUI");
        frame.setContentPane(this.panel1);
        frame.setBounds(100, 200, 1200, 500);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        text1.setText(user.getUsername());
        text2.setText(user.getId_card());
        text3.setText(user.getCnp());
        text4.setText(user.getName());
        text5.setText(user.getAddress());
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameUpd = text1.getText();
                String passwordUpd = String.copyValueOf(pass1.getPassword());
                String cardUpd = text2.getText();
                String cnpUpd = text3.getText();
                String nameUpd = text4.getText();
                String adrUpd = text5.getText();

                User userOld = userBLL.findUserById(user.getUser_id());
                User userNew = new User(user.getUser_type(), usernameUpd, passwordUpd, nameUpd, cardUpd, cnpUpd, adrUpd);
                userNew.setUser_id(user.getUser_id());

                if(String.copyValueOf(pass1.getPassword()).equals("")) {
                    throw new NoSuchElementException("Password field cannot be empty.");
                }

                userBLL.updateUser(userOld, userNew);
            }
        });
        showProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1 = new JTable(new DefaultTableModel(new Object[] {"Product ID", "Product Name", "Price"}, 0));
                DefaultTableModel model = (DefaultTableModel)table1.getModel();
                List<Product> allProducts = productBLL.findAllProducts();
                for(Product it: allProducts) {
                    model.addRow(new Object[] {it.getProduct_id(), it.getName(), it.getPrice()});
                }
                product.setViewportView(table1);
            }
        });
        startOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double totalP = 0;

                order = new Order(user.getUser_id(), totalP, "adr", 0, "1999-06-17");
                orderBLL.insertOrder(order);
                order = orderBLL.findOrderByDate("1999-06-17").get(0);
                textField2.setText(String.valueOf(order.getOrder_id()));
            }
        });
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = textField6.getText();
                int productQuantity = Integer.valueOf(textField1.getText());

                Product productToAdd = productBLL.findProductByName(productName).get(0);

                OrderItem orderItem = new OrderItem(order.getOrder_id(), productName, productQuantity);

                orderItemBLL.insertOrderItem(orderItem);
            }
        });
        showCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table3 = new JTable(new DefaultTableModel(new Object[] {"Product Name", "Quantity", "Price"}, 0));
                DefaultTableModel model = (DefaultTableModel)table3.getModel();
                List<OrderItem> currentItems = orderItemBLL.findOrderItemByOrderId(order.getOrder_id());
                double total = 0;
                for(OrderItem it: currentItems) {
                    Product productInCart = productBLL.findProductByName(it.getProduct_name()).get(0);
                    model.addRow(new Object[] {it.getProduct_name(), it.getQuantity(), String.valueOf(it.getQuantity() * productInCart.getPrice())});
                    total += it.getQuantity() * productInCart.getPrice();
                }
                User current = userBLL.findUserById(user.getUser_id());
                if(current.getUser_type() == 2) {
                    total *= 0.9;
                }
                all.setViewportView(table3);
                textField5.setText(String.valueOf(total));
            }
        });
        cardRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pay = 1;
                cashRadioButton.setSelected(false);
            }
        });
        cashRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pay = 0;
                cardRadioButton.setSelected(false);
            }
        });
        submitOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField4.getText().equals("")) {
                    throw new NoSuchElementException("Input a date");
                }
                String date = textField4.getText();
                order.setPayment(pay);
                order.setAddress(user.getAddress());
                order.setDateBuy(date);
                List<OrderItem> currentItems = orderItemBLL.findOrderItemByOrderId(order.getOrder_id());
                double total = 0;
                for(OrderItem it: currentItems) {
                    Product productInCart = productBLL.findProductByName(it.getProduct_name()).get(0);
                    total += it.getQuantity() * productInCart.getPrice();
                }
                User current = userBLL.findUserById(user.getUser_id());
                if(current.getUser_type() == 2) {
                    total *= 0.9;
                }
                order.setTotal(total);

                orderBLL.updateOrder(order);
            }
        });
        showMyOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table4 = new JTable(new DefaultTableModel(new Object[] {"Order ID", "Date", "Address", "Payment", "Total"}, 0));
                DefaultTableModel model = (DefaultTableModel)table4.getModel();
                List<Order> ordersByDate = orderBLL.findOrderByClientId(user.getUser_id());
                for(Order it : ordersByDate) {
                    String payment = new String();
                    if (it.getPayment() == 0) payment = "Cash";
                    else if (it.getPayment() == 1) payment = "Card";
                    model.addRow(new Object[] {it.getOrder_id(), it.getDateBuy(), it.getAddress(), payment, Double.valueOf(it.getTotal())});
                }
                byDate.setViewportView(table4);
            }
        });
        showOrdersByDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table4 = new JTable(new DefaultTableModel(new Object[] {"Order ID", "Date", "Address", "Payment", "Total"}, 0));
                DefaultTableModel model = (DefaultTableModel)table4.getModel();
                String date = textField3.getText();
                List<Order> ordersByDate = orderBLL.findOrderByDate(date);
                for(Order it : ordersByDate) {
                    if (it.getClient_id() == user.getUser_id()) {
                        String payment = new String();
                        if (it.getPayment() == 0) payment = "Cash";
                        else if (it.getPayment() == 1) payment = "Card";
                        model.addRow(new Object[] {it.getOrder_id(), it.getDateBuy(), it.getAddress(), payment, Double.valueOf(it.getTotal())});
                    }
                }
                byDate.setViewportView(table4);
            }
        });
    }
}
