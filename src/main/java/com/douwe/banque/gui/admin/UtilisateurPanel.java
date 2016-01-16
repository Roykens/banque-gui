package com.douwe.banque.gui.admin;

import com.douwe.banque.data.OperationType;
import com.douwe.banque.data.RoleType;
import com.douwe.banque.gui.MainMenuPanel;
import com.douwe.banque.model.Operation;
import com.douwe.banque.model.User;
import com.douwe.banque.service.IBanqueAdminService;
import com.douwe.banque.service.IBanqueCommonService;
import com.douwe.banque.service.ServiceException;
import com.douwe.banque.service.impl.BanqueAdminServiceImpl;
import com.douwe.banque.service.impl.BanqueServiceCommonImpl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
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
public class UtilisateurPanel extends JPanel {

    private JButton nouveauBtn;
    private JButton supprimerBtn;
    private JButton initialiserPasswdBtn;
    private JButton filtreBtn;
    private JTextField loginText;
    private JComboBox<RoleType> role;
    private JTable utilisateurTable;
    private DefaultTableModel tableModel;
    private MainMenuPanel parent;
    private IBanqueAdminService adminService;
    private IBanqueCommonService commonService;

    public UtilisateurPanel(MainMenuPanel parentFrame)  {
        super();
        try {
            adminService = new BanqueAdminServiceImpl();
            commonService = new BanqueServiceCommonImpl();
            setLayout(new BorderLayout());
            this.parent = parentFrame;
            JPanel haut = new JPanel();
            haut.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel lbl;
            haut.add(lbl = new JLabel("LA LISTE DES UTILISATEURS DE MA BANQUE POPULAIRE"));
            lbl.setFont(new Font("Times New Roman", Font.ITALIC, 18));
            add(BorderLayout.BEFORE_FIRST_LINE, haut);
            JPanel contenu = new JPanel();
            contenu.setLayout(new BorderLayout());
            JPanel bas = new JPanel();
            bas.setLayout(new FlowLayout());
            nouveauBtn = new JButton("Nouveau");
            supprimerBtn = new JButton("Supprimer");
            initialiserPasswdBtn = new JButton("Reinitialiser Mot de Passe");
            filtreBtn = new JButton("Filtrer");
            filtreBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        String username = loginText.getText();
                        RoleType roleN = (RoleType) role.getSelectedItem();
                        StringBuilder query = new StringBuilder("select * from users where status = ?");
                        if ((username != null) && !("".equals(username))) {
                            query.append("and username like '%");
                            query.append(username);
                            query.append("%'");
                        }
                        if (roleN != null) {
                            query.append("and role = ");
                            query.append(roleN.ordinal());
                        }
                        List<User> users = adminService.findUserByNameAndRole(username, roleN);
                        tableModel.setRowCount(0);
                        for (User user : users) {
                            tableModel.addRow(new Object[]{user.getId(),
                                user.getLogin(),
                                user.getRole()});
                        }
                    } catch (ServiceException ex) {
                        Logger.getLogger(UtilisateurPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            nouveauBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    
                        parent.setContenu(new NouveauUtilisateurPanel(parent));
                }
            });
            initialiserPasswdBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    int selected = utilisateurTable.getSelectedRow();
                    if (selected >= 0) {
                        try {
                            User user = adminService.findUserById((Integer) tableModel.getValueAt(selected, 0));
                            user.setPassword("admin");
                            adminService.saveOrUpdateUser(user);
                            JOptionPane.showMessageDialog(null, "Le mot de passe est reinitialisé à 'admin'");
                        } catch (ServiceException ex) {
                            Logger.getLogger(UtilisateurPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Aucun utilisateur n'est selectionné");
                    }
                }
            });
            supprimerBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    int selected = utilisateurTable.getSelectedRow();
                    if (selected >= 0) {
                        try {   
                            User u = adminService.findUserById((Integer) tableModel.getValueAt(selected, 0));
                            adminService.deleteUser((Integer) tableModel.getValueAt(selected, 0));
                            Operation o = new Operation();
                            o.setAccount(null);
                            o.setDateOperation( new Date(new java.util.Date().getTime()));
                            o.setDescription("Suppression de l'utilisateur " + tableModel.getValueAt(selected, 1));
                            o.setType(OperationType.suppression);
                            o.setUser(u);
                            commonService.saveOperation(o);
                            tableModel.removeRow(selected);
                            
                        } catch (ServiceException ex) {
                            JOptionPane.showMessageDialog(null, "Impossible de supprimer cet utilisateur");
                            Logger.getLogger(UtilisateurPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Aucun utilisateur n'est selectionné");
                    }
                }
            });
            bas.add(nouveauBtn);
            bas.add(initialiserPasswdBtn);
            bas.add(supprimerBtn);
            JPanel filtrePanel = new JPanel();
            filtrePanel.setLayout(new FlowLayout());
            filtrePanel.add(new JLabel("Nom Client"));
            filtrePanel.add(loginText = new JTextField());
            loginText.setPreferredSize(new Dimension(100, 25));
            filtrePanel.add(new JLabel("Type Compte"));
            filtrePanel.add(role = new JComboBox<RoleType>());
            role.setPreferredSize(new Dimension(100, 25));
            role.addItem(null);
            role.addItem(RoleType.customer);
            role.addItem(RoleType.employee);
            role.addItem(RoleType.admin);
            filtrePanel.add(filtreBtn);
            contenu.add(BorderLayout.AFTER_LAST_LINE, bas);
            contenu.add(BorderLayout.BEFORE_FIRST_LINE, filtrePanel);
            tableModel = new DefaultTableModel(new Object[]{"id", "Login", "Role"}, 0);
            utilisateurTable = new JTable(tableModel);
            utilisateurTable.removeColumn(utilisateurTable.getColumnModel().getColumn(0));
            contenu.add(BorderLayout.CENTER, new JScrollPane(utilisateurTable));
            add(BorderLayout.CENTER, contenu);
            List<User> users = adminService.findAllUsers();
            for (User user : users) {
                tableModel.addRow(new Object[]{user.getId(),
                    user.getLogin(),
                    user.getRole()});
            }
        } catch (ServiceException ex) {
            Logger.getLogger(UtilisateurPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}