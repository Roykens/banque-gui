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
    private static final  String SCHEMASQLITE ="jdbc:sqlite:banque.db";

    private ConnectToDB() {
    }
    
    public static Connection getConnectionToSQLite() throws SQLException{
        return DriverManager.getConnection(SCHEMASQLITE);
    }
}
