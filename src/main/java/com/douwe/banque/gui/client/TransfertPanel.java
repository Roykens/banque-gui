package com.douwe.banque.gui.client;

import com.douwe.banque.gui.MainMenuPanel;
import com.douwe.banque.gui.common.UserInfo;
import com.douwe.banque.model.Account;
import com.douwe.banque.service.IBanqueClientService;
import com.douwe.banque.service.ServiceException;
import com.douwe.banque.service.impl.BanqueClientServiceImpl;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Vincent Douwe<douwevincent@yahoo.fr>
 */
public class TransfertPanel extends JPanel {

    private JComboBox<String> source;
    private JTextField destination;
    private JTextField amount;
    private JButton transferBtn;
    private MainMenuPanel parent;
    private IBanqueClientService clientService;

    public TransfertPanel(MainMenuPanel parentFrame) {
        super();
        try {
        
            clientService = new BanqueClientServiceImpl();
            
            setLayout(new BorderLayout());
            this.parent = parentFrame;
            JPanel pan = new JPanel(new FlowLayout(FlowLayout.CENTER));
            Label lbl;
            pan.add(lbl = new Label("NOUVEAU TRANSFERT DE COMPTE A COMPTE"));
            lbl.setFont(new Font("Times New Roman", Font.ITALIC, 18));
            add(BorderLayout.BEFORE_FIRST_LINE, pan);
            DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout("right:max(40dlu;p), 12dlu, 180dlu:", ""));
            builder.append("Compte Départ", source = new JComboBox<String>());
            builder.append("Compte Destination", destination = new JTextField());
            builder.append("Montant", amount = new JTextField());
            builder.append(transferBtn = new JButton("Transferer"));
            transferBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    String init = (String) source.getSelectedItem();
                    String dest = destination.getText();
                    String amt = amount.getText();
                    if ("".equalsIgnoreCase(init)) {
                        JOptionPane.showMessageDialog(null, "Le compte source est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else if ("".equalsIgnoreCase(dest)) {
                        JOptionPane.showMessageDialog(null, "Le compte destination est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else if ("".equalsIgnoreCase(amt)) {
                        JOptionPane.showMessageDialog(null, "Le montant est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            double value = Double.valueOf(amt);
                            clientService.transfer(init, dest, value, UserInfo.getUserId());
                        } catch (NumberFormatException ps) {
                            JOptionPane.showMessageDialog(null, "Le montant doit etre un nombre", "Erreur", JOptionPane.ERROR_MESSAGE);
                        } catch (ServiceException ex) {
                            JOptionPane.showMessageDialog(null, "Erreur lors du transfert", "Erreur", JOptionPane.ERROR_MESSAGE);
                            Logger.getLogger(TransfertPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                }
            });
            add(BorderLayout.CENTER, builder.getPanel());
            source.addItem("");
             List<Account> accounts = clientService.findAccountByCustomerId(UserInfo.getCustomerId());
        for (Account account : accounts) {    
            source.addItem(account.getAccountNumber());
        }
        } catch (ServiceException ex) {
            Logger.getLogger(TransfertPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
