package com.douwe.banque.gui.admin;

import com.douwe.banque.data.OperationType;
import com.douwe.banque.gui.MainMenuPanel;
import com.douwe.banque.model.Customer;
import com.douwe.banque.service.IBanqueAdminService;
import com.douwe.banque.service.ServiceException;
import com.douwe.banque.service.impl.BanqueAdminServiceImpl;
import com.douwe.banque.util.ModelDeBasePanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
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
public class ClientPanel  extends JPanel{

    private JButton nouveauBtn;
    private JButton supprimerBtn;
    private JButton modifierBtn;
    private JButton filtreBtn;
    private JTable clientTable;
    private DefaultTableModel tableModel;
    private JTextField nameText;
    private MainMenuPanel parent;
    private IBanqueAdminService adminService;

    public ClientPanel(MainMenuPanel parentFrame){
       // super();
        try {
            adminService = new BanqueAdminServiceImpl();
            setLayout(new BorderLayout());
            this.parent = parentFrame;
            JPanel haut = new JPanel();
            haut.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel lbl;
            haut.add(lbl = new JLabel("LA LISTE DES CLIENTS DE MA BANQUE POPULAIRE"));
            lbl.setFont(new Font("Times New Roman", Font.ITALIC, 18));
            add(BorderLayout.BEFORE_FIRST_LINE, haut);
            JPanel contenu = new JPanel();
            contenu.setLayout(new BorderLayout());
            JPanel bas = new JPanel();
            bas.setLayout(new FlowLayout());
            nouveauBtn = new JButton("Nouveau");
            supprimerBtn = new JButton("Supprimer");
            modifierBtn = new JButton("Modifier");
            filtreBtn = new JButton("Filtrer");
            filtreBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    selectCustomerbyStatus();
                }

                private void selectCustomerbyStatus() throws HeadlessException {
                    String name = nameText.getText();
                    try {
                        List<Customer> customers = adminService.findCustomerByName(name);                            
                            tableModel.setRowCount(0);
                            for (Customer customer : customers) {                                
                                tableModel.addRow(new Object[]{customer.getId(),
                                    customer.getName(),
                                    customer.getEmailAddress(),
                                    customer.getPhoneNumber()
                                });
                            }
                    } catch (ServiceException ex) {
                        Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            nouveauBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    parent.setContenu(new NouveauClientPanel(parent));
                }
            });
            modifierBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    int selected = clientTable.getSelectedRow();
                    if (selected >= 0) {                      
                            parent.setContenu(new NouveauClientPanel(parent, (Integer) tableModel.getValueAt(selected, 0)));                       
                    } else {
                        JOptionPane.showMessageDialog(null, "Aucun client n'est selectionné");
                    }
                }
            });
            supprimerBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    int selected = clientTable.getSelectedRow();
                    if (selected >= 0) {
                        try {
                            adminService.deleteCustomer((Integer)tableModel.getValueAt(selected, 0));                            
                            tableModel.removeRow(selected);
//                            PreparedStatement pst3 = conn.prepareStatement("insert into operations(operationType, dateOperation,description, account_id, user_id) values (?,?,?,?,?)");
//                            pst3.setInt(1, OperationType.suppression.ordinal());
//                            pst3.setDate(2, new Date(new java.util.Date().getTime()));
//                            pst3.setString(3, "Suppression du client " + tableModel.getValueAt(selected, 1));
//                            pst3.setInt(4, 1);
//                            pst3.setInt(5, 1);
//                            pst3.executeUpdate();
//                            pst3.close();
//                            conn.commit();
//                            conn.close();
                        } catch (ServiceException ex) {
                            Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Aucune donnée n'est selectionnée");
                    }
                }
            });
            bas.add(nouveauBtn);
            bas.add(modifierBtn);
            bas.add(supprimerBtn);
            JPanel filtrePanel = new JPanel();
            filtrePanel.setLayout(new FlowLayout());
            filtrePanel.add(new JLabel("Nom"));
            filtrePanel.add(nameText = new JTextField());
            nameText.setPreferredSize(new Dimension(100, 25));
            filtrePanel.add(filtreBtn);
            contenu.add(BorderLayout.AFTER_LAST_LINE, bas);
            contenu.add(BorderLayout.BEFORE_FIRST_LINE, filtrePanel);
            tableModel = new DefaultTableModel(new Object[]{"id", "Nom", "Adresse Email", "Téléphone"}, 0);
            clientTable = new JTable(tableModel);
            clientTable.removeColumn(clientTable.getColumnModel().getColumn(0));
            contenu.add(BorderLayout.CENTER, new JScrollPane(clientTable));
            add(BorderLayout.CENTER, contenu);
            List<Customer> customers = adminService.findAllCustomer();
            for (Customer customer : customers) {
                tableModel.addRow(new Object[]{customer.getId(),
                    customer.getName(),
                    customer.getEmailAddress(),
                    customer.getPhoneNumber()
                });
            }
        } catch (ServiceException ex) {
            Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
}
