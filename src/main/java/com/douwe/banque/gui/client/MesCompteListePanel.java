package com.douwe.banque.gui.client;

import com.douwe.banque.gui.common.UserInfo;
import com.douwe.banque.model.Account;
import com.douwe.banque.service.IBanqueClientService;
import com.douwe.banque.service.ServiceException;
import com.douwe.banque.service.impl.BanqueClientServiceImpl;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vincent Douwe<douwevincent@yahoo.fr>
 */
public class MesCompteListePanel extends JPanel {

    private JTable compteTable;
    private DefaultTableModel model;
    private static final String request = "select * from account where customer_id=?";
    private IBanqueClientService clientService;

    public MesCompteListePanel() {
        super();
        try {
            clientService = new BanqueClientServiceImpl();
            setLayout(new BorderLayout(10, 10));
            JPanel pan = new JPanel(new FlowLayout(FlowLayout.CENTER));
            Label lbl;
            pan.add(lbl = new Label("LA LISTE DE MES COMPTES"));
            lbl.setFont(new Font("Times New Roman", Font.ITALIC, 18));
            add(BorderLayout.BEFORE_FIRST_LINE, pan);
            JPanel btnPanel = new JPanel();
            btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            model = new DefaultTableModel(new String[]{"No Compte", "Type Compte", "Balance"}, 0);
            compteTable = new JTable(model);
            add(BorderLayout.CENTER, new JScrollPane(compteTable));
            List<Account> accounts = clientService.findAccountByCustomerId(UserInfo.getCustomerId());
            for (Account account : accounts) {
                model.addRow(new Object[]{account.getAccountNumber(), account.getType(), account.getBalance()});
            }
            
        } catch (ServiceException ex) {
            Logger.getLogger(MesCompteListePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
