package com.douwe.banque.gui.admin;

import com.douwe.banque.data.AccountType;
import com.douwe.banque.data.OperationType;
import com.douwe.banque.gui.MainMenuPanel;
import com.douwe.banque.model.Account;
import com.douwe.banque.model.Operation;
import com.douwe.banque.model.User;
import com.douwe.banque.model.projection.AccountCustomer;
import com.douwe.banque.service.IBanqueAdminService;

import com.douwe.banque.service.exception.ServiceException;
import com.douwe.banque.service.IBanqueCommonService;
import com.douwe.banque.service.impl.BanqueAdminServiceImpl;
import com.douwe.banque.service.impl.BanqueServiceCommonImpl;
import com.douwe.banque.util.MessageHelper;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vincent Douwe<douwevincent@yahoo.fr>
 */
public class ComptePanel extends JPanel {

    private JButton nouveauBtn;
    private JButton supprimerBtn;
    private JButton modifierBtn;
    private JButton filtreBtn;
    private JTable compteTable;
    private DefaultTableModel tableModel;
    private JTextField nameText;
    private JTextField numberText;
    private JComboBox<AccountType> type;
    private transient MainMenuPanel mainMenuPanel;
    private transient IBanqueAdminService adminService;
    private transient MessageHelper helper;
    private transient IBanqueCommonService commonService;

    public ComptePanel(MainMenuPanel parentFrame) {
        super();
        helper = new MessageHelper();
        try {
            adminService = new BanqueAdminServiceImpl();
            commonService = new BanqueServiceCommonImpl();
            helper = new MessageHelper();
            this.mainMenuPanel = parentFrame;
            setLayout(new BorderLayout());
            JPanel haut = new JPanel();
            haut.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel lbl  = new JLabel(helper.getProperty("comptePanel.liste"));
            haut.add(lbl);
            lbl.setFont(new Font("Times New Roman", Font.ITALIC, 18));
            add(BorderLayout.BEFORE_FIRST_LINE, haut);
            JPanel contenu = new JPanel();
            contenu.setLayout(new BorderLayout());
            JPanel bas = new JPanel();
            bas.setLayout(new FlowLayout());
            nouveauBtn = new JButton(helper.getProperty("comptePanel.nouveau"));
            supprimerBtn = new JButton(helper.getProperty("comptePanel.supprimer"));
            modifierBtn = new JButton(helper.getProperty("comptePanel.modifier"));
            filtreBtn = new JButton(helper.getProperty("comptePanel.filtrer"));
            filtreBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        String client = nameText.getText();
                        String accountNumber = numberText.getText();
                        AccountType ty = (AccountType) type.getSelectedItem();
                        List<AccountCustomer> values = adminService.findAccountByCriteria(client, accountNumber, ty);
                        tableModel.setRowCount(0);
                        for (AccountCustomer accountCustomer : values) {
                            tableModel.addRow(new Object[]{accountCustomer.getId(),
                                accountCustomer.getAccountNumber(),
                                accountCustomer.getBalance(),
                                accountCustomer.getDateDeCreation(),
                                accountCustomer.getType(),
                                accountCustomer.getCustomerName()});
                        }
                    } catch (ServiceException ex) {
                        Logger.getLogger(ComptePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            nouveauBtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    mainMenuPanel.setContenu(new NouveauComptePanel(mainMenuPanel));

                }
            });
            modifierBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    int selected = compteTable.getSelectedRow();
                    if (selected >= 0) {

                        mainMenuPanel.setContenu(new NouveauComptePanel(mainMenuPanel, (Integer) tableModel.getValueAt(selected, 0)));

                    } else {

                        JOptionPane.showMessageDialog(null, helper.getProperty("comptePanel.aucunCompte"));

                    }
                }
            });
            supprimerBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    int selected = compteTable.getSelectedRow();
                    if (selected >= 0) {
                        try {
                            String accountNumber = (String) tableModel.getValueAt(selected, 1);                            
                            Account acc = adminService.findAccountByNumber(accountNumber);
                            User user = adminService.getUserByAccount(acc.getId());
                            adminService.deleteAccount(acc.getId());
                            Operation ope = new Operation();
                            ope.setDateOperation(new Date(new java.util.Date().getTime()));
                            ope.setAccount(acc);
                            ope.setDescription("Cloture du compte " + accountNumber);
                            ope.setType(OperationType.CLOTURE);
                            ope.setUser(user);
                            commonService.saveOperation(ope);
                            tableModel.removeRow(selected);
                        } catch (ServiceException ex) {
                            Logger.getLogger(ComptePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {

                        JOptionPane.showMessageDialog(null, helper.getProperty("comptePanel.aucuneLigne"));
                    }

                }
            });
            bas.add(nouveauBtn);
            bas.add(modifierBtn);
            bas.add(supprimerBtn);
            JPanel filtrePanel = new JPanel();
            filtrePanel.setLayout(new FlowLayout());
            filtrePanel.add(new JLabel(helper.getProperty("comptePanel.filter.nom")));
            nameText = new JTextField();
            filtrePanel.add(nameText);
            nameText.setPreferredSize(new Dimension(100, 25));
            filtrePanel.add(new JLabel(helper.getProperty("comptePanel.filter.numero")));
            numberText = new JTextField();
            filtrePanel.add(numberText);
            numberText.setPreferredSize(new Dimension(100, 25));
            filtrePanel.add(new JLabel(helper.getProperty("comptePanel.filter.type")));
            type = new JComboBox<AccountType>();
            filtrePanel.add(type);
            type.setPreferredSize(new Dimension(100, 25));
            type.addItem(null);
            type.addItem(AccountType.DEPOSIT);
            type.addItem(AccountType.SAVING);
            filtrePanel.add(filtreBtn);
            contenu.add(BorderLayout.AFTER_LAST_LINE, bas);
            contenu.add(BorderLayout.BEFORE_FIRST_LINE, filtrePanel);
            tableModel = new DefaultTableModel(new Object[]{helper.getProperty("comptePanel.table.id"), helper.getProperty("comptePanel.table.numero"), helper.getProperty("comptePanel.table.solde"), helper.getProperty("comptePanel.table.date"), helper.getProperty("comptePanel.table.type"), helper.getProperty("comptePanel.table.client")}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            compteTable = new JTable(tableModel);
            compteTable.removeColumn(compteTable.getColumnModel().getColumn(0));
            contenu.add(BorderLayout.CENTER, new JScrollPane(compteTable));
            add(BorderLayout.CENTER, contenu);
            List<AccountCustomer> values = adminService.findAllAccountCustomer();
            for (AccountCustomer accountCustomer : values) {
                tableModel.addRow(new Object[]{accountCustomer.getId(),
                    accountCustomer.getAccountNumber(),
                    accountCustomer.getBalance(),
                    accountCustomer.getDateDeCreation(),
                    accountCustomer.getType(),
                    accountCustomer.getCustomerName()});
            }
        } catch (ServiceException ex) {
            Logger.getLogger(ComptePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
