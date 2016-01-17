package com.douwe.banque.model;

import com.douwe.banque.data.OperationType;
import java.util.Date;

/**
 * Cette classe représente une Operation dans le système. Il est caractérisé par
 * le type d'opération qu'on a éffectué, la date de l'opération, le compte qui
 * effectue l'opération et le propriétaire du compte
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public class Operation {

    /**
     * L'identifiant de l'operation
     */
    private Integer id;

    /**
     * Le type d'opération traité
     */
    private OperationType type;

    /**
     * le jour et l'heure de l'opération
     */
    private Date dateOperation;

    /**
     * la description de l'opération
     */
    private String description;

    /**
     * Le compte qui éffectue l'opération
     */
    private Account account;

    /**
     * l'utilisateur responsable de l'opération
     */
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
