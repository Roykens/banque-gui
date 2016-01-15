package com.douwe.banque.gui.common;

import com.douwe.banque.data.OperationType;
import com.douwe.banque.data.RoleType;
import com.douwe.banque.model.Customer;
import com.douwe.banque.model.Operation;
import com.douwe.banque.model.User;
import com.douwe.banque.service.IBanqueCommonService;
import com.douwe.banque.service.ServiceException;
import com.douwe.banque.service.impl.BanqueServiceCommonImpl;
import com.douwe.banque.util.ModelDeBasePanel;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Vincent Douwe<douwevincent@yahoo.fr>
 */
public class LoginPanel extends ModelDeBasePanel {

    private JTextField loginText;
    private JPasswordField passwdText;
    private JButton btnLogin;
    private IBanqueCommonService commonService;

    public LoginPanel() throws SQLException{
        super();
        commonService = new BanqueServiceCommonImpl();
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(80, 350, 80, 300));
        JPanel haut = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lbl;
        haut.add(lbl = new JLabel("<html>Entrer login et mot de passe <br/> pour profiter des services de la banque populaire"));
        lbl.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        add(BorderLayout.BEFORE_FIRST_LINE, haut);
        DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout("right:max(40dlu;p), 12dlu, 180dlu:", ""));
        builder.append("Login", loginText = new JTextField());
        builder.append("Mot de passe", passwdText = new JPasswordField());
        builder.append(btnLogin = new JButton("Login"));
        add(BorderLayout.CENTER, builder.getPanel());
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    String username = loginText.getText();
                    String passwd = new String(passwdText.getPassword());
                    if ((username == null) || ("".equals(username))) {
                        JOptionPane.showMessageDialog(null, "Le login est obligatoire");
                        passwdText.setText("");
                        return;
                    }
                    if ("".equals(passwd)) {
                        JOptionPane.showMessageDialog(null, "Le mot de passe est obligatoire");
                        passwdText.setText("");
                        return;
                    }
                    User user = commonService.login(username, passwd);
                    if (user != null) {
                        UserInfo.setUsername(username);
                        UserInfo.setRole(user.getRole());
                        UserInfo.setUserId((int)user.getId());
                        UserInfo.setLogged(true);
                        if (UserInfo.getRole().equals(RoleType.customer)) {
                            Customer customer = commonService.findCustomerByLogin(username);
                            UserInfo.setCustomerId(customer.getId());
                        }
                        Operation operation = new Operation();
                        operation.setAccount(null);
                        operation.setDateOperation(new Date(new java.util.Date().getTime()));
                        operation.setDescription(username);
                        operation.setType(OperationType.connexion);
                        operation.setUser(user);
//                            PreparedStatement pst3 = conn.prepareStatement("insert into operations(operationType, dateOperation,description, account_id, user_id) values (?,?,?,?,?)");
//                            pst3.setInt(1, OperationType.connexion.ordinal());
//                            pst3.setDate(2, new Date(new java.util.Date().getTime()));
//                            pst3.setString(3, "Connection de l'utilisateur " + username);
//                            pst3.setInt(4, 1);
//                            pst3.setInt(5, 1);
//                            pst3.executeUpdate();
//                            pst3.close();
                            success();
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Login ou mot de passe incorrect");
                        passwdText.setText("");
                    }
                  
                  //  conn.close();
                } catch (ServiceException ex) {
                    Logger.getLogger(LoginPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
        });
    }

    public void success() {
    }
}
