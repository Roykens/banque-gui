package com.douwe.banque.gui.admin;

import com.douwe.banque.data.AccountType;
import com.douwe.banque.gui.MainMenuPanel;
import com.douwe.banque.model.Account;
import com.douwe.banque.model.projection.AccountCustomer;
import com.douwe.banque.service.IBanqueAdminService;
import com.douwe.banque.service.exception.ServiceException;
import com.douwe.banque.service.impl.BanqueAdminServiceImpl;
import com.douwe.banque.util.MessageHelper;
import com.douwe.banque.util.ModelDeBasePanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
    private MainMenuPanel parent;
    private IBanqueAdminService adminService;
    private final MessageHelper helper;

    public ComptePanel(MainMenuPanel parentFrame) {
        super();
        helper = new MessageHelper();
        try {
            adminService = new BanqueAdminServiceImpl();
            this.parent = parentFrame;
            setLayout(new BorderLayout());
            JPanel haut = new JPanel();
            haut.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel lbl;
            haut.add(lbl = new JLabel(helper.getProperty("comptePanel.liste")));
            lbl.setFont(new Font("Times New Roman", Font.ITALIC, 18));
            add(BorderLayout.BEFORE_FIRST_LINE, haut);
            JPanel contenu = new JPanel();
            contenu.setLayout(new BorderLayout());
            JPanel bas = new JPanel();
            bas.setLayout(new FlowLayout());
            nouveauBtn = new JButton(helper.getProperty("pourToutPanel.nouveau"));
            supprimerBtn = new JButton(helper.getProperty("pourToutPanel.supprimer"));
            modifierBtn = new JButton(helper.getProperty("pourToutPanel.modifier"));
            filtreBtn = new JButton(helper.getProperty("pourToutPanel.filtrer"));
            filtreBtn.addActionListener(new ActionListener() {
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
                public void actionPerformed(ActionEvent ae) {

                    parent.setContenu(new NouveauComptePanel(parent));
                }
            });
            modifierBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    int selected = compteTable.getSelectedRow();
                    if (selected >= 0) {

                        parent.setContenu(new NouveauComptePanel(parent, (Integer) tableModel.getValueAt(selected, 0)));

                    } else {
                        JOptionPane.showMessageDialog(null, helper.getProperty("comptePanel.showMessageAucunCompte"));
                    }
                }
            });
            supprimerBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    int selected = compteTable.getSelectedRow();
                    if (selected >= 0) {
                        try {
                            String accountNumber = (String) tableModel.getValueAt(selected, 1);
                            //conn.setAutoCommit(false);
                            Account acc = adminService.findAccountByNumber(accountNumber);
                            adminService.deleteAccount(acc.getId());
//                            Operation ope = new Operation();
//                            ope.setDateOperation(new Date(new java.util.Date().getTime()));
//                            ope.setAccount(acc);
//                            ope.setDescription("Cloture du compte " + accountNumber);
//                            ope.setType(OperationType.cloture);
//                            ope.setUser(null);
//                            PreparedStatement st = conn.prepareStatement("insert into operations(operationType,dateOperation,description,account_id, user_id) values(?,?,?,?,?)");
//                            st.setInt(1, OperationType.cloture.ordinal());
//                            st.setDate(2, new Date(new java.util.Date().getTime()));
//                            st.setString(3, "Cloture du compte " + accountNumber);
//                            st.setInt(4, (Integer) tableModel.getValueAt(selected, 0));
//                            st.setInt(5, 1);
//                            st.executeUpdate();
//                            pst.executeUpdate();
//                            conn.commit();
//                            pst.close();
//                            conn.close();
//                            tableModel.removeRow(selected);
                        } catch (ServiceException ex) {
                            Logger.getLogger(ComptePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,  helper.getProperty("comptePanel.showMessageAucuneLigne"));
                    }

                }
            });
            bas.add(nouveauBtn);
            bas.add(modifierBtn);
            bas.add(supprimerBtn);
            JPanel filtrePanel = new JPanel();
            filtrePanel.setLayout(new FlowLayout());
            filtrePanel.add(new JLabel("Nom Client"));
            filtrePanel.add(nameText = new JTextField());
            nameText.setPreferredSize(new Dimension(100, 25));
            filtrePanel.add(new JLabel("Numero Compte"));
            filtrePanel.add(numberText = new JTextField());
            numberText.setPreferredSize(new Dimension(100, 25));
            filtrePanel.add(new JLabel("Type Compte"));
            filtrePanel.add(type = new JComboBox<AccountType>());
            type.setPreferredSize(new Dimension(100, 25));
            type.addItem(null);
            type.addItem(AccountType.deposit);
            type.addItem(AccountType.saving);
            filtrePanel.add(filtreBtn);
            contenu.add(BorderLayout.AFTER_LAST_LINE, bas);
            contenu.add(BorderLayout.BEFORE_FIRST_LINE, filtrePanel);
            tableModel = new DefaultTableModel(new Object[]{"id", "Numero Compte", "Solde", "Date création", "Type", "Client"}, 0) {
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
