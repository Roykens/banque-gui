package com.douwe.banque.gui;

import com.douwe.banque.data.OperationType;
import com.douwe.banque.gui.common.LoginPanel;
import com.douwe.banque.gui.common.UserInfo;
import com.douwe.banque.model.Operation;
import com.douwe.banque.model.User;
import com.douwe.banque.service.IBanqueAdminService;
import com.douwe.banque.service.IBanqueCommonService;
import com.douwe.banque.service.ServiceException;
import com.douwe.banque.service.impl.BanqueAdminServiceImpl;
import com.douwe.banque.service.impl.BanqueServiceCommonImpl;
import com.douwe.banque.util.MessageHelper;
import java.awt.BorderLayout;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Vincent Douwe <douwevincent@yahoo.fr>
 */
public class MainFrame extends JFrame{

    private HeaderPanel headerPanel;
    private JPanel contentPanel;
    private final MessageHelper helper = new MessageHelper();
    private IBanqueCommonService commonService ;
    private IBanqueAdminService adminService;

    public MainFrame()  {
        super();
        commonService = new BanqueServiceCommonImpl();
        adminService = new BanqueAdminServiceImpl();
        setTitle(helper.getProperty("mainFrame.titre"));
        getContentPane().setLayout(new BorderLayout(10, 10));
        headerPanel = new HeaderPanel() {
            @Override
            public void deconnexion() {
                baniere();
                contentPanel.validate();
            }

            private void baniere() {
                try {
                    contentPanel.removeAll();
                    contentPanel.add(BorderLayout.CENTER, new LoginPanel() {
                        @Override
                        public void success() {
                            contentPanel.removeAll();
                            contentPanel.add(BorderLayout.CENTER, new MainMenuPanel());
                            contentPanel.validate();
                            headerPanel.setEnabledHeader(true);
                        }
                    });
                    Operation o = new Operation();
                    User u = adminService.findUserByLogin(UserInfo.getUsername().toLowerCase());
                    o.setAccount(null);
                    o.setDateOperation(new Date(new java.util.Date().getTime()));
                    o.setDescription(helper.getProperty("mainFrame.deconnecteUtilisateur") + UserInfo.getUsername());
                    o.setType(OperationType.deconnexion);
                    o.setUser(u);
                    commonService.saveOperation(o);
                } catch (ServiceException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        getContentPane().add(headerPanel, BorderLayout.BEFORE_FIRST_LINE);
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        JPanel login = new LoginPanel() {
            @Override
            public void success() {
                contentPanel.removeAll();
                contentPanel.add(BorderLayout.CENTER, new MainMenuPanel());
                contentPanel.validate();
                headerPanel.setEnabledHeader(true);
            }
        };
        contentPanel.add(login, BorderLayout.CENTER);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
