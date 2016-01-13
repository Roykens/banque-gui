/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.douwe.banque.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Shelby
 */
public class ConnectToDB {
    private final static String schemaSqlite="jdbc:sqlite:banque.db";
    
    public static Connection getConnectionToSQLite() throws SQLException{
        return DriverManager.getConnection(schemaSqlite);
    }
}
