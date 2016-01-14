package com.douwe.banque.model;

import com.douwe.banque.data.RoleType;
import java.util.Objects;

/**
 * Cette classe représente un utilisateur du système.
 * Il est caractérisé par un login et un mot de passe.
 * Un utilisateur est d'un type bien connu.
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public class User {
    
    /**
     * L'identifiant de l'utilisateur
     */
    private Integer id;
    
    /**
     * Le login utilisé par l'utilisateur
     */
    private String login;
    
    /**
     * Le mot de passe de l'utilisateur
     */
    private String password;
    
    /**
     * Le role de l'utilisateur.
     */
    private RoleType role;
    
    private int status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", login=" + login + ", password=" + password + ", role=" + role + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (this.role != other.role) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return true;
    }
    
    
}
