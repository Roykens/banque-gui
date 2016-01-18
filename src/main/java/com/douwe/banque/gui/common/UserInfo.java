package com.douwe.banque.gui.common;

import com.douwe.banque.data.RoleType;

/**
 *
 * @author Vincent Douwe<douwevincent@yahoo.fr>
 */
public class UserInfo {
    
    private static String username;
    private static int userId;
    private static RoleType role;
    private static int customerId;
    private static boolean logged = false;
    

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserInfo.username = username;
    }

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        UserInfo.userId = userId;
    }

    public static RoleType getRole() {
        return role;
    }

    public static void setRole(RoleType role_id) {
        UserInfo.role = role_id;
    }

    public static boolean isLogged() {
        return logged;
    }

    public static void setLogged(boolean logged) {
        UserInfo.logged = logged;
    }

    public static int getCustomerId() {
        return customerId;
    }

    public static void setCustomerId(int customerId) {
        UserInfo.customerId = customerId;
    }
}