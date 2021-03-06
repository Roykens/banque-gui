package com.douwe.banque.gui.admin;

import com.douwe.banque.data.OperationType;
import com.douwe.banque.data.RoleType;
import com.douwe.banque.gui.MainMenuPanel;
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
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Vincent Douwe<douwevincent@yahoo.fr>
 */
public class NouveauUtilisateurPanel extends JPanel {

    private JTextField loginText;
    private JPasswordField passwdText1;
    private JPasswordField passwdText2;
    private JComboBox<RoleType> role;
    private JButton enregistrer;
    private transient MainMenuPanel mainMenuPanel;
    private transient IBanqueAdminService adminService;
    private transient IBanqueCommonService commonService;

    public NouveauUtilisateurPanel(MainMenuPanel mainMenuPanelFrame) {

        super();
        adminService = new BanqueAdminServiceImpl();
        commonService = new BanqueServiceCommonImpl();
        setLayout(new BorderLayout(20, 20));
        this.mainMenuPanel = mainMenuPanelFrame;
        JPanel haut = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lbl = new JLabel("AJOUT D'UN NOUVEAU UTILISATEUR DANS MA BANQUE POPULAIRE");
        haut.add(lbl );
        lbl.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        add(BorderLayout.BEFORE_FIRST_LINE, haut);
        DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout("right:max(40dlu;p), 12dlu, 180dlu:", ""));
        loginText = new JTextField();
        builder.append("Login",loginText );
        passwdText1 = new JPasswordField();
        builder.append("Mot de Passe",passwdText1 );
        passwdText2 = new JPasswordField();
        builder.append("Retapez mot de passe",passwdText2 );
        role = new JComboBox<RoleType>();
        builder.append("Role",role );
        role.addItem(null);
        role.addItem(RoleType.ADMIN);
        role.addItem(RoleType.EMPLOYEE);
        enregistrer = new JButton("Enregistrer");
        builder.append(enregistrer);
        add(BorderLayout.CENTER, builder.getPanel());
        enregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    String login = loginText.getText();
                    String pwd = new String(passwdText1.getPassword());
                    String pwd2 = new String(passwdText2.getPassword());
                    RoleType ro = (RoleType) role.getSelectedItem();
                    if ((login == null) || ("".equals(login))) {
                        JOptionPane.showMessageDialog(null, "Le login est obligatoire");
                        return;
                    }
                    if ("".equals(pwd)) {
                        JOptionPane.showMessageDialog(null, "Le password est obligatoire");
                        return;
                    }
                    if (!(pwd.equals(pwd2))) {
                        JOptionPane.showMessageDialog(null, "Les mots de passe ne sont pas identiques");
                        return;
                    }
                    if (ro == null) {
                        JOptionPane.showMessageDialog(null, "Le role est obligatoire");
                        return;
                    }
                    User user = new User();
                    user.setLogin(login.toLowerCase());
                    user.setPassword(pwd);
                    user.setRole(ro);
                    adminService.saveOrUpdateUser(user);
                    user = adminService.findUserByLogin(login.toLowerCase());
                    Operation o = new Operation();
                    o.setAccount(null);
                    o.setDateOperation(new Date(new java.util.Date().getTime()));
                    o.setType(OperationType.DEBIT);
                    o.setUser(user);
                    o.setDescription("Ajout de l'utilisateur " + login.toLowerCase());
                    commonService.saveOperation(o);
                    mainMenuPanel.setContenu(new UtilisateurPanel(mainMenuPanel));
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, "Impossible de créer le compte");
                    Logger.getLogger(NouveauUtilisateurPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
