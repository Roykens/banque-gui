package com.douwe.banque.gui.admin;

import com.douwe.banque.gui.MainMenuPanel;
import com.douwe.banque.model.Customer;
import com.douwe.banque.service.IBanqueAdminService;
import com.douwe.banque.service.exception.ServiceException;
import com.douwe.banque.service.impl.BanqueAdminServiceImpl;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Vincent Douwe<douwevincent@yahoo.fr>
 */
public class NouveauClientPanel extends JPanel{

    private JTextField nameText;
    private JTextField emailText;
    private JTextField phoneText;
    private JButton btnEnregistrer;
    private int id = -1;
    private transient MainMenuPanel parent;
    private transient Customer customer;
    private transient  IBanqueAdminService adminService;

    public NouveauClientPanel(MainMenuPanel parentFrame, int id) {
        this(parentFrame);
        this.id = id;
        if (id > 0) {
            try {
                adminService = new BanqueAdminServiceImpl();
                customer = adminService.findCustomerById(id);
                if (customer != null) {
                    nameText.setText(customer.getName());
                    emailText.setText(customer.getEmailAddress());
                    phoneText.setText(customer.getPhoneNumber());
                }
            } catch (ServiceException ex) {
                Logger.getLogger(NouveauClientPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public NouveauClientPanel(MainMenuPanel parentFrame){
       // super();
         adminService = new BanqueAdminServiceImpl();
        this.parent = parentFrame;
        setLayout(new BorderLayout(10, 10));
        JPanel haut = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lbl;
        haut.add(lbl = new JLabel("AJOUT D'UN NOUVEAU CLIENT DANS MA BANQUE POPULAIRE"));
        lbl.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        add(BorderLayout.BEFORE_FIRST_LINE, haut);
        DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout("right:max(40dlu;p), 12dlu, 180dlu:", ""));
        builder.append("Nom", nameText = new JTextField());
        builder.append("Adresse Email", emailText = new JTextField());
        builder.append("Numéro de Téléphone", phoneText = new JTextField());
        builder.append(btnEnregistrer = new JButton("Enrégistrer"));
        add(BorderLayout.CENTER, builder.getPanel());
        btnEnregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (id <= 0) {
                    try {
                        String name = nameText.getText();
                        String email = emailText.getText();
                        String phone = phoneText.getText();
                        if ((name == null) || ("".equals(name))) {
                            JOptionPane.showMessageDialog(null, "Le nom est obligatoire");
                            return;
                        }
                        if ((email == null) || ("".equals(email))) {
                            JOptionPane.showMessageDialog(null, "L'adresse email est obligatoire");
                            return;
                        }
                        if ((phone == null) || ("".equals(phone))) {
                            JOptionPane.showMessageDialog(null, "Le numéro de téléphone est obligatoire");
                            return;
                        }
                        Customer customer = new Customer();
                        customer.setEmailAddress(email);
                        customer.setPhoneNumber(phone);
                        customer.setName(name);
                        Customer cust = adminService.saveOrUpdateCustomer(customer);
//                        conn.setAutoCommit(false);
//                        PreparedStatement pst = conn.prepareStatement("insert into customer(name,emailAddress, phoneNumber, user_id) values (?,?,?,?)");
//                        PreparedStatement st = conn.prepareStatement("insert into users(username, passwd, role) values (?,?,?)");
//                        String val = name.replaceAll(" ", "").toLowerCase();
//                        st.setString(1, val);
//                        st.setString(2, "admin");
//                        st.setInt(3, RoleType.customer.ordinal());
//                        st.executeUpdate();
//                        ResultSet rrr = st.getGeneratedKeys();
//                        if (rrr.next()) {
//                            pst.setString(1, name);
//                            pst.setString(2, email);
//                            pst.setString(3, phone);
//                            pst.setInt(4, rrr.getInt(1));
//                            pst.executeUpdate();
//                            PreparedStatement pst3 = conn.prepareStatement("insert into operations(operationType, dateOperation,description, account_id, user_id) values (?,?,?,?,?)");
//                            pst3.setInt(1, OperationType.ajout.ordinal());
//                            pst3.setDate(2, new Date(new java.util.Date().getTime()));
//                            pst3.setString(3, "Ajout du client " + name);
//                            pst3.setInt(4, 1);
//                            pst3.setInt(5, 1);
//                            pst3.executeUpdate();
//                            pst3.setInt(1, OperationType.ajout.ordinal());
//                            pst3.setDate(2, new Date(new java.util.Date().getTime()));
//                            pst3.setString(3, "Ajout de l'utilisateur " + val);
//                            pst3.setInt(4, 1);
//                            pst3.setInt(5, 1);
//                            pst3.executeUpdate();
//                            pst3.close();
//                            conn.commit();
//                        } else {
//                            conn.rollback();
//                        }
//                        rrr.close();
//                        st.close();
//                        pst.close();
//                        conn.close();
                        if (cust.getUser() != null) {
                            String val = cust.getUser().getLogin();
                            JOptionPane.showMessageDialog(null, "Un compte avec login " + val + " et mot de passe 'admin' a été créé");
                        }
                        parent.setContenu(new ClientPanel(parent));
                    } catch (ServiceException ex) {
                        JOptionPane.showMessageDialog(null, "Impossible de créer le compte");
                        Logger.getLogger(NouveauClientPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        String name = nameText.getText();
                        String email = emailText.getText();
                        String phone = phoneText.getText();
                        if ((name == null) || ("".equals(name))) {
                            JOptionPane.showMessageDialog(null, "Le nom est obligatoire");
                            return;
                        }
                        if ((email == null) || ("".equals(email))) {
                            JOptionPane.showMessageDialog(null, "L'adresse email est obligatoire");
                            return;
                        }
                        if ((phone == null) || ("".equals(phone))) {
                            JOptionPane.showMessageDialog(null, "Le numéro de téléphone est obligatoire");
                            return;
                        }
                        
                        customer.setName(name);
                        customer.setPhoneNumber(phone);
                        customer.setEmailAddress(email);
                        adminService.saveOrUpdateCustomer(customer);
//                        PreparedStatement pst = conn.prepareStatement("update customer set name =?, emailAddress=?, phoneNumber=? where id = ?");
//                        pst.setString(1, name);
//                        pst.setString(2, email);
//                        pst.setString(3, phone);
//                        pst.setInt(4, id);
//                        pst.executeUpdate();
//                        pst.close();
//                        conn.close();
                        parent.setContenu(new ClientPanel(parent));
//                    } catch (SQLException ex) {
//                        JOptionPane.showMessageDialog(null, "Impossible de mettre a jour votre compte");
//                        Logger.getLogger(NouveauClientPanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ServiceException ex) {
                        JOptionPane.showMessageDialog(null, "Impossible de mettre a jour votre compte");
                        Logger.getLogger(NouveauClientPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
}
