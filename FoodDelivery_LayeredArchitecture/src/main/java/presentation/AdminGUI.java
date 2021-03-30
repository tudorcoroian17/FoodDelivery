package presentation;

import businessLogic.OrderBLL;
import businessLogic.ProductBLL;
import businessLogic.UserBLL;
import model.Order;
import model.Product;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdminGUI {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTabbedPane tabbedPane2;
    private JButton showProductsButton;
    private JTable table1;
    private JScrollPane product;
    private JPanel insertProd;
    private JPanel updateProd;
    private JPanel deleteProd;
    private JButton insertProductButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton updateProductButton;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton deleteProductButton;
    private JTextField textField6;
    private JButton showCustomersButton;
    private JTable table2;
    private JScrollPane customer;
    private JPasswordField passwordField1;
    private JTextField textField7;
    private JRadioButton adminRadioButton;
    private JRadioButton clientRadioButton;
    private JButton insertUserButton;
    private JButton updateUserButton;
    private JTextField textField8;
    private JTabbedPane tabbedPane3;
    private JTabbedPane tabbedPane4;
    private JButton showOrdersButton;
    private JButton showOrdersButton1;
    private JButton showOrdersButton2;
    private JButton updateCustomerButton;
    private JTextField textField9;
    private JRadioButton yesRadioButton;
    private JTable table3;
    private JTable table4 = new JTable(new DefaultTableModel(new Object[] {"Order ID", "Customer ID", "Date", "Address", "Payment", "Total"}, 0));
    private JTable table5 = new JTable(new DefaultTableModel(new Object[] {"Order ID", "Customer ID", "Date", "Address", "Payment", "Total"}, 0));
    private JScrollPane all;
    private JScrollPane byDate;
    private JScrollPane byCustomer;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField textField15;
    private JTextField text1;
    private JPasswordField pass1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;
    private JRadioButton mkAdmin;
    private JRadioButton mkClient;
    private JTextField text10;
    private JButton deleteUser;

    private User user;
    private ProductBLL productBLL;
    private UserBLL userBLL;
    private OrderBLL orderBLL;
    private int admin;
    private int updateAdmin;
    private int loyal = 0;

    public AdminGUI(User user) {
        this.user = user;
        productBLL = new ProductBLL();
        userBLL = new UserBLL();
        orderBLL = new OrderBLL();
        JFrame frame = new JFrame("AdminGUI");
        frame.setContentPane(this.panel1);
        frame.setBounds(100,200,1200,500);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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
        showCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table2 = new JTable(new DefaultTableModel(new Object[] {"User ID", "User Type", "Username", "Name", "ID Card", "CNP", "Address"}, 0));
                DefaultTableModel model = (DefaultTableModel)table2.getModel();
                List<User> allUsers = userBLL.findAllUsers();
                for(User it : allUsers) {
                    String userType = new String();
                    if (it.getUser_type() == 0) userType = "Admin";
                    else if (it.getUser_type() == 1) userType = "Customer";
                    else if (it.getUser_type() == 2) userType = "Loyal Customer";
                    model.addRow(new Object[] {it.getUser_id(), userType, it.getUsername(), it.getName(), it.getId_card(), it.getCnp(), it.getAddress()});
                }
                customer.setViewportView(table2);
            }
        });
        showOrdersButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table3 = new JTable(new DefaultTableModel(new Object[] {"Order ID", "Customer ID", "Date", "Address", "Payment", "Total"}, 0));
                DefaultTableModel model = (DefaultTableModel)table3.getModel();
                List<Order> allOrders = orderBLL.findAllOrders();
                for(Order it : allOrders) {
                    String payment = new String();
                    if (it.getPayment() == 0) payment = "Cash";
                    else if (it.getPayment() == 1) payment = "Card";
                    model.addRow(new Object[] {it.getOrder_id(), it.getClient_id(), it.getDateBuy(), it.getAddress(), payment, Double.valueOf(it.getTotal())});
                }
                all.setViewportView(table3);
            }
        });
        showOrdersButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table4 = new JTable(new DefaultTableModel(new Object[] {"Order ID", "Customer ID", "Date", "Address", "Payment", "Total"}, 0));
                DefaultTableModel model = (DefaultTableModel)table4.getModel();
                String date = textField10.getText();
                List<Order> ordersByDate = orderBLL.findOrderByDate(date);
                for(Order it : ordersByDate) {
                    String payment = new String();
                    if (it.getPayment() == 0) payment = "Cash";
                    else if (it.getPayment() == 1) payment = "Card";
                    model.addRow(new Object[] {it.getOrder_id(), it.getClient_id(), it.getDateBuy(), it.getAddress(), payment, Double.valueOf(it.getTotal())});
                }
                byDate.setViewportView(table4);
            }
        });
        showOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table5 = new JTable(new DefaultTableModel(new Object[] {"Order ID", "Customer ID", "Date", "Address", "Payment", "Total"}, 0));
                DefaultTableModel model = (DefaultTableModel)table5.getModel();
                String customerID = textField11.getText();
                List<Order> ordersByCustomer = orderBLL.findOrderByClientId(Integer.valueOf(customerID));
                for(Order it : ordersByCustomer) {
                    String payment = new String();
                    if (it.getPayment() == 0) payment = "Cash";
                    else if (it.getPayment() == 1) payment = "Card";
                    model.addRow(new Object[] {it.getOrder_id(), it.getClient_id(), it.getDateBuy(), it.getAddress(), payment, Double.valueOf(it.getTotal())});
                }
                byCustomer.setViewportView(table5);
            }
        });
        insertProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prodName = textField1.getText();
                double prodPrice = Double.valueOf(textField2.getText());

                Product product = new Product(prodName, prodPrice);
                productBLL.insertProduct(product);

                //reset
                textField1.setText("");
                textField2.setText("");
            }
        });
        updateProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product oldProduct = productBLL.findProductById(Integer.valueOf(textField3.getText()));
                String newProdName = textField4.getText();
                double newProdPrice = Double.valueOf(textField5.getText());
                Product newProduct = new Product(newProdName, newProdPrice);
                newProduct.setProduct_id(Integer.valueOf(textField3.getText()));
                productBLL.update(oldProduct, newProduct);

                //reset
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
            }
        });
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int prodID = Integer.valueOf(textField6.getText());
                productBLL.deleteProductById(prodID);

                //reset
                textField6.setText("");
            }
        });
        insertUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameSig = textField12.getText();
                String passwordSig = new String(passwordField1.getPassword());
                String idCardNbSig = textField13.getText();
                String cnpSig      = textField14.getText();
                String nameSig     = textField15.getText();
                String addressSig  = textField7.getText();

                User user = new User(admin, usernameSig, passwordSig, nameSig, idCardNbSig, cnpSig, addressSig);
                UserBLL userBLL = new UserBLL();
                userBLL.insertUser(user);

                //reset
                textField12.setText("");
                textField13.setText("");
                textField14.setText("");
                textField15.setText("");
                textField7.setText("");
                passwordField1.setText("");
                adminRadioButton.setSelected(false);
                clientRadioButton.setSelected(false);
            }
        });
        adminRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin = 0;
                clientRadioButton.setSelected(false);
            }
        });
        clientRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin = 1;
                adminRadioButton.setSelected(false);
            }
        });
        updateUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameSig = text1.getText();
                String passwordSig = new String(pass1.getPassword());
                String idCardNbSig = text2.getText();
                String cnpSig      = text3.getText();
                String nameSig     = text4.getText();
                String addressSig  = text5.getText();
                int userID = Integer.valueOf(textField8.getText());

                User user = new User(updateAdmin, usernameSig, passwordSig, nameSig, idCardNbSig, cnpSig, addressSig);
                user.setUser_id(userID);
                User userOld = userBLL.findUserById(userID);
                UserBLL userBLL = new UserBLL();
                userBLL.updateUser(userOld, user);

                //reset
                text1.setText("");
                text2.setText("");
                text3.setText("");
                text4.setText("");
                text5.setText("");
                textField8.setText("");
                pass1.setText("");
                mkAdmin.setSelected(false);
                mkClient.setSelected(false);
            }
        });
        mkAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAdmin = 0;
                mkClient.setSelected(false);
            }
        });
        mkClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAdmin = 1;
                mkAdmin.setSelected(false);
            }
        });
        deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userID = Integer.valueOf(text10.getText());
                userBLL.deleteUserById(userID);

                //reset
                text10.setText("");
            }
        });
        updateCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int customerID = Integer.valueOf(textField9.getText());
                User customer = userBLL.findUserById(customerID);
                if (loyal == 1) {
                    User newCustomer = new User(2, customer.getUsername(), customer.getPassword(), customer.getName(), customer.getId_card(), customer.getCnp(), customer.getAddress());
                    newCustomer.setUser_id(customer.getUser_id());
                    userBLL.updateUser(customer, newCustomer);
                }
                yesRadioButton.setSelected(false);
                loyal = 0;
            }
        });
        yesRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(yesRadioButton.isSelected()) {
                    loyal = 1;
                }
            }
        });
    }
}
