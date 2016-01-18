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
public class NouveauClientPanel extends JPanel {

    private JTextField nameText;
    private JTextField emailText;
    private JTextField phoneText;
    private JButton btnEnregistrer;
    private int id = -1;
    private transient MainMenuPanel mainMenuPanel;
    private transient Customer customer;
    private transient IBanqueAdminService adminService;

    public NouveauClientPanel(MainMenuPanel mainMenuPanelFrame, int id) {
        this(mainMenuPanelFrame);
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

    public NouveauClientPanel(MainMenuPanel mainMenuPanelFrame) {
        adminService = new BanqueAdminServiceImpl();
        this.mainMenuPanel = mainMenuPanelFrame;
        setLayout(new BorderLayout(10, 10));
        JPanel haut = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lbl = new JLabel("AJOUT D'UN NOUVEAU CLIENT DANS MA BANQUE POPULAIRE");
        haut.add(lbl );
        lbl.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        add(BorderLayout.BEFORE_FIRST_LINE, haut);
        DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout("right:max(40dlu;p), 12dlu, 180dlu:", ""));
        nameText = new JTextField();
        builder.append("Nom",nameText );
        emailText = new JTextField();
        builder.append("Adresse Email",emailText );
        phoneText = new JTextField();
        builder.append("Numéro de Téléphone",phoneText );
        btnEnregistrer = new JButton("Enrégistrer");
        builder.append(btnEnregistrer);
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
                        if (cust.getUser() != null) {
                            String val = cust.getUser().getLogin();
                            JOptionPane.showMessageDialog(null, "Un compte avec login " + val + " et mot de passe 'admin' a été créé");
                        }
                        mainMenuPanel.setContenu(new ClientPanel(mainMenuPanel));
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
                        adminService.saveOrUpdateCustomer(customer);;
                        mainMenuPanel.setContenu(new ClientPanel(mainMenuPanel));
                    } catch (ServiceException ex) {
                        JOptionPane.showMessageDialog(null, "Impossible de mettre a jour votre compte");
                        Logger.getLogger(NouveauClientPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        );
    }
}
