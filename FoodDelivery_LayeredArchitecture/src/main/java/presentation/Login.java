package presentation;

import businessLogic.UserBLL;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

public class Login extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField textField2;
    private JButton loginButton;
    private JButton signupButton;
    private JTextField textField3;
    private JTextField textField5;
    private JTextField textField6;
    private JPasswordField passwordField1;
    private JTextField textField4;
    private JTextField textField7;
    private JRadioButton adminRadioButton;
    private JRadioButton clientRadioButton;

    public String usernameLog;
    public String passwordLog;

    public String usernameSig;
    public String passwordSig;
    public String idCardNbSig;
    public String cnpSig;
    public String nameSig;
    public String addressSig;
    public int admin = -1;      // 0 => admin
                                // 1 => client
                                // 2 => client with discount


    public Login() {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameLog = textField1.getText();
                passwordLog = new String(textField2.getPassword());

                UserBLL userBLL = new UserBLL();
                User user = userBLL.findUserByUsername(usernameLog);
                if (user == null) {
                    throw new NoSuchElementException("User " + usernameLog + " does not have an account.");
                }
                if (user.getPassword().compareTo(passwordLog) != 0) {
                    throw new NoSuchElementException("Incorrect password.");
                }
                switch (user.getUser_type()) {
                    case 0:
                        AdminGUI admin = new AdminGUI(user);
                        break;
                    case 1:
                        CustomerGUI customerWOD = new CustomerGUI(user);
                        break;
                    case 2:
                        CustomerGUI customerWD = new CustomerGUI(user);
                        break;
                }
            }
        });
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameSig = textField3.getText();
                passwordSig = new String(passwordField1.getPassword());
                idCardNbSig = textField5.getText();
                cnpSig      = textField6.getText();
                nameSig     = textField4.getText();
                addressSig  = textField7.getText();

                User user = new User(admin, usernameSig, passwordSig, nameSig, idCardNbSig, cnpSig, addressSig);
                UserBLL userBLL = new UserBLL();
                userBLL.insertUser(user);

                //reset
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
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
    }
}
