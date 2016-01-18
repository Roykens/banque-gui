package com.douwe.banque.gui;

import com.douwe.banque.data.RoleType;
import com.douwe.banque.gui.admin.ClientPanel;
import com.douwe.banque.gui.admin.ComptePanel;
import com.douwe.banque.gui.admin.NouveauDebitPanel;
import com.douwe.banque.gui.admin.NouveauDepotPanel;
import com.douwe.banque.gui.admin.OperationsPanel;
import com.douwe.banque.gui.admin.UtilisateurPanel;
import com.douwe.banque.gui.client.MesCompteListePanel;
import com.douwe.banque.gui.client.MesOperationsListePanel;
import com.douwe.banque.gui.client.TransfertPanel;
import com.douwe.banque.gui.common.UserInfo;
import com.douwe.banque.util.MessageHelper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.jdesktop.swingx.JXHyperlink;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

/**
 *
 * @author Vincent Douwe <douwevincent@yahoo.fr>
 */
public class MainMenuPanel extends JPanel {

    private transient JXHyperlink mesComptes;
    private transient  JXHyperlink mesOperations;
    private transient  JXHyperlink tranfer;
    private transient  JXHyperlink utilisateurs;
    private transient  JXHyperlink comptes;
    private transient  JXHyperlink customer;
    private transient  JXHyperlink operations;
    private final transient  JXHyperlink profil;
    private transient  JXHyperlink debit;
    private transient  JXHyperlink credit;
    private final transient  JPanel container;
     private final transient   MessageHelper helper = new MessageHelper();
  

    public MainMenuPanel() {
        setLayout(new BorderLayout());
        mesComptes = new JXHyperlink();
        mesComptes.setText(helper.getProperty("mainMenuPanel.mesComptes"));
        mesComptes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                    setContenu(new MesCompteListePanel());
                
            }
        });
        mesComptes.setUnclickedColor(Color.blue);
        mesComptes.setClickedColor(Color.blue);
        mesOperations = new JXHyperlink();
        mesOperations.setText(helper.getProperty("mainMenuPanel.mesOperations"));
        mesOperations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
             
                    setContenu(new MesOperationsListePanel());
                
            }
        });
        mesOperations.setUnclickedColor(Color.blue);
        mesOperations.setClickedColor(Color.blue);
        tranfer = new JXHyperlink();
        tranfer.setText(helper.getProperty("mainMenuPanel.transfert"));
        tranfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                    setContenu(new TransfertPanel(MainMenuPanel.this));
                
            }
        });
        tranfer.setUnclickedColor(Color.blue);
        tranfer.setClickedColor(Color.blue);
        utilisateurs = new JXHyperlink() {
            @Override
            public boolean isVisible() {
                return UserInfo.getRole() == RoleType.ADMIN;
            }
        };
        utilisateurs.setText(helper.getProperty("mainMenuPanel.utilisateurs"));
        utilisateurs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                    setContenu(new UtilisateurPanel(MainMenuPanel.this));
                
            }
        });
        utilisateurs.setUnclickedColor(Color.blue);
        utilisateurs.setClickedColor(Color.blue);
        comptes = new JXHyperlink();
        comptes.setText(helper.getProperty("mainMenuPanel.comptes"));
        comptes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                    setContenu(new ComptePanel(MainMenuPanel.this));
                
            }
        });
        comptes.setUnclickedColor(Color.blue);
        comptes.setClickedColor(Color.blue);
        operations = new JXHyperlink() {
            @Override
            public boolean isVisible() {
                return UserInfo.getRole() == RoleType.ADMIN;
            }
        };
        operations.setText(helper.getProperty("mainMenuPanel.operations"));
        operations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              
                    setContenu(new OperationsPanel());
                
            }
        });
        operations.setUnclickedColor(Color.blue);
        operations.setClickedColor(Color.blue);
        customer = new JXHyperlink();
        customer.setText(helper.getProperty("mainMenuPanel.clients"));
        customer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              
                    setContenu(new ClientPanel(MainMenuPanel.this));
               
            }
        });
        customer.setUnclickedColor(Color.blue);
        customer.setClickedColor(Color.blue);
        JXTaskPaneContainer menu = new JXTaskPaneContainer();
        JXTaskPane clientPane = new JXTaskPane() {
            @Override
            public boolean isVisible() {
                return UserInfo.getRole() == RoleType.CUSTOMER;
            }
        };
        clientPane.setTitle(helper.getProperty("mainMenuPanel.espaceClient"));
        clientPane.add(mesComptes);
        clientPane.add(mesOperations);
        clientPane.add(tranfer);
        menu.add(clientPane);
        JXTaskPane employee = new JXTaskPane() {
            @Override
            public boolean isVisible() {
                return UserInfo.getRole() == RoleType.EMPLOYEE;
            }
        };
        employee.setTitle(helper.getProperty("mainMenuPanel.espaceEmploye"));
        debit = new JXHyperlink();
        employee.add(debit);
        debit.setText(helper.getProperty("mainMenuPanel.debit"));
        debit.setUnclickedColor(Color.blue);
        debit.setClickedColor(Color.blue);
        debit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                    setContenu(new NouveauDebitPanel(MainMenuPanel.this));               
            }
        });
        credit = new JXHyperlink();
        employee.add(credit);
        credit.setText(helper.getProperty("mainMenuPanel.credit"));
        credit.setUnclickedColor(Color.blue);
        credit.setClickedColor(Color.blue);
        credit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {setContenu(new NouveauDepotPanel(MainMenuPanel.this));
            }
        });
        menu.add(employee);
        JXTaskPane adminPane = new JXTaskPane() {
            @Override
            public boolean isVisible() {
                return (UserInfo.getRole() == RoleType.ADMIN) || (UserInfo.getRole() == RoleType.EMPLOYEE);
            }
        };
        adminPane.setTitle(helper.getProperty("mainMenuPanel.admin"));
        adminPane.add(comptes);
        adminPane.add(customer);
        adminPane.add(operations);
        adminPane.add(utilisateurs);
        menu.add(adminPane);
        JXTaskPane profilPane = new JXTaskPane();
        profilPane.setTitle(helper.getProperty("mainMenuPanel.profil"));
        profil = new JXHyperlink();
        profilPane.add(profil);
        profil.setText(helper.getProperty("mainMenuPanel.profil"));
        profil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null, "Si j'implémente tout alors que feront mes étudiants?");
            }
        });
        profil.setUnclickedColor(Color.blue);
        profil.setClickedColor(Color.blue);
        menu.add(profilPane);
        add(menu, BorderLayout.BEFORE_LINE_BEGINS);
        container = new JPanel();
        container.setLayout(new BorderLayout());
        container.setBorder(new EmptyBorder(2, 20, 20, 20));        
        add(container, BorderLayout.CENTER);
    }

    public void setContenu(JPanel pan) {
        container.removeAll();
        container.add(BorderLayout.CENTER, pan);
        container.validate();
    }
}
