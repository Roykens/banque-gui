package com.douwe.banque.model;

import java.util.Objects;

/**
 * Cette classe représente un client de la banque dans le système. Il est
 * caractérisé par son nom ,son adresse mail et est également representer comme
 * un utilisateur du système de la banque
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public class Customer {

    /**
     * L'identifiant du client
     */
    private Integer id;

    /**
     * le nom et le prénom du client
     */
    private String name;

    /**
     * l' adresse mail du client
     */
    private String emailAddress;

    /**
     * le numéro de téléphone du client
     */
    private String phoneNumber;

    /**
     * le status : actif ou inactif
     */
    private int status;

    /**
     * l'utilisateur que reprensente le client dans la système
     */
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.emailAddress);
        hash = 97 * hash + Objects.hashCode(this.phoneNumber);
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
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.emailAddress, other.emailAddress)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        return true;
    }
    
    

}
