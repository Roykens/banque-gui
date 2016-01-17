package com.douwe.banque.gui;

import com.douwe.banque.gui.common.UserInfo;
import com.douwe.banque.util.MessageHelper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXHyperlink;

/**
 *
 * @author Vincent Douwe <douwevincent@yahoo.fr>
 */
public abstract class HeaderPanel extends JPanel{

    private JXHyperlink deconnexion;
    private JLabel username;
    private final MessageHelper helper = new MessageHelper();
    
    public HeaderPanel(){
        setLayout(new FlowLayout(FlowLayout.RIGHT,10,10));        
        setBackground(new Color(166, 202, 240)); 
        setPreferredSize(new Dimension(800,180));
        username = new JLabel(helper.getProperty("header.presentation"));
        deconnexion = new JXHyperlink();
        deconnexion.setUnclickedColor(Color.blue);
        deconnexion.setClickedColor(Color.blue);
        deconnexion.setText(helper.getProperty("header.deconnexion"));
        deconnexion.setVisible(false);
        username.setVisible(false);
        add(username);
        add(deconnexion); 
        deconnexion.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                deconnexion();
                setEnabledHeader(false);
            }
        });
    }
    
    public abstract void deconnexion();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font font = new Font("Serif", Font.PLAIN, 32);
        GradientPaint gp = new GradientPaint(0f,0f,Color.blue,0f,30f,Color.green);
        ((Graphics2D)g).setPaint(gp);
        g.setFont(font);
        g.setColor(Color.yellow);
        g.drawString(helper.getProperty("header.titre"),310,80);
        g.setFont(new Font("Serif", Font.ITALIC, 24));
        g.setColor(Color.CYAN);
        g.drawString(helper.getProperty("header.design"), 390, 120);
    }

    public void setEnabledHeader(boolean status){
        username.setVisible(status);
        deconnexion.setVisible(status);
        if(status){
              username.setText(helper.getProperty("header.presentation")+UserInfo.getUsername()+", ");
        }
    }

    
}
