/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.douwe.banque.util;

import com.sun.xml.internal.ws.developer.Serialization;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JFrame;


/**
 *
 * @author Shelby
 */
public class ModelDeBaseFrame extends JFrame{
    @Serialization
    protected Connection conn;
    
    public ModelDeBaseFrame() throws SQLException{
        super();
        this.conn=ConnectToDB.getConnectionToSQLite();
    }
}