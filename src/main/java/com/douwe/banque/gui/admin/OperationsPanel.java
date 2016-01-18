package com.douwe.banque.gui.admin;

import com.douwe.banque.data.OperationType;
import com.douwe.banque.gui.MainMenuPanel;
import com.douwe.banque.model.projection.AccountOperation;
import com.douwe.banque.service.IBanqueAdminService;

import com.douwe.banque.service.exception.ServiceException;
import com.douwe.banque.util.MessageHelper;

import com.douwe.banque.service.IBanqueCommonService;
import com.douwe.banque.service.impl.BanqueAdminServiceImpl;
import com.douwe.banque.service.impl.BanqueServiceCommonImpl;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Vincent Douwe<douwevincent@yahoo.fr>
 */
public class OperationsPanel extends JPanel {

    private JTable operationTable;
    private DefaultTableModel tableModel;
    private JTextField clientText;
    private JTextField compteText;
    private JComboBox<OperationType> type;
    private JButton filtreBtn;
    private transient JXDatePicker startDate;
    private transient JXDatePicker endDate;
    private transient MessageHelper helper;
    private transient IBanqueAdminService adminService;
    private transient IBanqueCommonService commonService;

    public OperationsPanel() {
        super();
        helper = new MessageHelper();
        try {
            adminService = new BanqueAdminServiceImpl();
            commonService = new BanqueServiceCommonImpl();
            setLayout(new BorderLayout());
            JPanel haut = new JPanel();
            haut.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel lbl;
            haut.add(lbl = new JLabel(helper.getProperty("operationsPanel.liste")));
            lbl.setFont(new Font("Times New Roman", Font.ITALIC, 18));
            add(BorderLayout.BEFORE_FIRST_LINE, haut);
            JPanel contenu = new JPanel();
            contenu.setLayout(new BorderLayout());
            JPanel filtrePanel = new JPanel();
            filtreBtn = new JButton(helper.getProperty("pourToutPanel.filtrer"));
            filtreBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        String selectedCompte = compteText.getText();
                        OperationType selectedOperation = (OperationType) type.getSelectedItem();
                        String client = clientText.getText();
                        Date debut = startDate.getDate();
                        Date fin = endDate.getDate();
                        List<AccountOperation> operations = commonService.findOperationByCriteria(selectedCompte, client, selectedOperation, debut, fin);
                        tableModel.setNumRows(0);
                        for (AccountOperation accountOperation : operations) {
                            tableModel.addRow(new Object[]{accountOperation.getType(),
                                accountOperation.getAccountNumber(),
                                accountOperation.getDateOperation(),
                                accountOperation.getUsername(),
                                accountOperation.getDescription()});
                        }
                    } catch (ServiceException ex) {
                        Logger.getLogger(OperationsPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            filtrePanel.setLayout(new FlowLayout());
            filtrePanel.add(new JLabel("Nom Client"));
            filtrePanel.add(clientText = new JTextField());
            clientText.setPreferredSize(new Dimension(100, 25));
            filtrePanel.add(new JLabel("Numero Compte"));
            filtrePanel.add(compteText = new JTextField());
            compteText.setPreferredSize(new Dimension(100, 25));
            filtrePanel.add(new JLabel("Type Opération"));
            filtrePanel.add(type = new JComboBox<OperationType>());
            type.setPreferredSize(new Dimension(100, 25));
            type.addItem(null);
            for (int i = 0; i < OperationType.values().length; i++) {
                type.addItem(OperationType.values()[i]);
            }
            filtrePanel.add(new JLabel("Début"));
            filtrePanel.add(startDate = new JXDatePicker());
            filtrePanel.add(new JLabel("Fin"));
            filtrePanel.add(endDate = new JXDatePicker());
            filtrePanel.add(filtreBtn);
            contenu.add(BorderLayout.BEFORE_FIRST_LINE, filtrePanel);
            tableModel = new DefaultTableModel(new Object[]{"Opération", "Compte", "Date", "Utilisateur", "Description"}, 0);
            operationTable = new JTable(tableModel);
            contenu.add(BorderLayout.CENTER, new JScrollPane(operationTable));
            add(BorderLayout.CENTER, contenu);
            List<AccountOperation> ops = adminService.findAllOperations();
            for (AccountOperation accountOperation : ops) {
                tableModel.addRow(new Object[]{accountOperation.getType(),
                    accountOperation.getAccountNumber(),
                    accountOperation.getDateOperation(),
                    accountOperation.getUsername(),
                    accountOperation.getDescription()});
            }
        } catch (ServiceException ex) {
            Logger.getLogger(OperationsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
