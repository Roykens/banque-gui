package com.douwe.banque.gui.admin;

import com.douwe.banque.data.OperationType;
import com.douwe.banque.gui.MainMenuPanel;
import com.douwe.banque.gui.common.EmptyPanel;
import com.douwe.banque.gui.common.UserInfo;
import com.douwe.banque.model.Account;
import com.douwe.banque.model.Operation;
import com.douwe.banque.model.User;
import com.douwe.banque.service.IBanqueAdminService;
import com.douwe.banque.service.IBanqueCommonService;
import com.douwe.banque.service.exception.ServiceException;
import com.douwe.banque.service.impl.BanqueAdminServiceImpl;
import com.douwe.banque.service.impl.BanqueServiceCommonImpl;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
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
public class NouveauDepotPanel extends JPanel {
    
    private JTextField accountText;
    private JTextField amountText;
    private JButton btnEnregistrer;
    private MainMenuPanel parent;
    
    private IBanqueAdminService adminService ;
    private IBanqueCommonService commonService;
    private Connection conn;

    public NouveauDepotPanel(MainMenuPanel parentFrame)  {
        super();
        adminService = new BanqueAdminServiceImpl();
        commonService= new BanqueServiceCommonImpl();
        setLayout(new BorderLayout(10, 10));
        this.parent = parentFrame;
        JPanel haut = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lbl;
        haut.add(lbl = new JLabel("CRÉDIT DE COMPTE DANS MA BANQUE POPULAIRE"));
        lbl.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        add(BorderLayout.BEFORE_FIRST_LINE, haut);
        DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout("right:max(40dlu;p), 12dlu, 180dlu:", ""));
        builder.append("Numéro Compte", accountText = new JTextField());
        builder.append("Montant", amountText = new JTextField());
        builder.append(btnEnregistrer = new JButton("Enrégistrer"));
        add(BorderLayout.CENTER, builder.getPanel());
        btnEnregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    String account = accountText.getText();
                    String amountS = amountText.getText();
                    double amount;
                    if ((account == null) || ("".equals(account))) {
                        JOptionPane.showMessageDialog(null, "Le numéro de compte est obligatoire");
                        return;
                    }
                    if ((amountS == null) || ("".equals(amountS))) {
                        JOptionPane.showMessageDialog(null, "Le montant est obligatoire");
                        return;
                    }
                    try {
                        amount = Double.valueOf(amountS);
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Le montant doit etre un nombre positif");
                        return;
                    }
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(null, "Le montant doit etre un nombre positif");
                        return;
                    }
                    Account acc = adminService.findAccountByNumber(account);
                    if(acc != null){
                        adminService.credit(account, amount, UserInfo.getUserId());
                        Operation o = new Operation();
                        o.setAccount(acc);
                        o.setDateOperation(new Date(new java.util.Date().getTime()));
                        o.setDescription("Credit du compte " + account + " de " + amount);
                        o.setType(OperationType.credit);
                        User u = adminService.findUserById(UserInfo.getUserId());
                        o.setUser(u);
                        commonService.saveOperation(o);
                        JOptionPane.showMessageDialog(null, "Opération réalisée avec succès");
                            parent.setContenu(EmptyPanel.emptyPanel());
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Le compte specifié n'existe pas");
                    }
                } catch (ServiceException ex) {
                    Logger.getLogger(NouveauDepotPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}