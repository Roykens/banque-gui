package com.douwe.banque.model;

import com.douwe.banque.data.AccountType;
import java.io.Serializable;
import java.util.Date;

/**
 * Cette classe représente un compte bancaire d'un utilisateur du système. 
 * Il est caractérisé par un numéro de compte , un solde et l'utilisateur du compte.
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public class Account implements Serializable {

    /**
     * L'identifiant d'un compte
     */
    private Integer id;

    /**
     * le numéro du compte
     */
    private String accountNumber;

    /**
     * Le solde du compte
     */
    private double balance;

    /**
     * le date de creation du compte
     */
    private Date dateDeCreation;

    /**
     * le type de compte : courant ou depot
     */
    private AccountType type;

    /**
     * l'utilisateur du compte
     */
    private Customer customer;

    /**
     * le status du compte : active ou inactif
     */
    private int status;

    public Account(String accountNumber, double balance, Date dateDeCreation, AccountType type, Customer customer, int status) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.dateDeCreation = dateDeCreation;
        this.type = type;
        this.customer = customer;
        this.status = status;

    }

    public Account() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(Date dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
