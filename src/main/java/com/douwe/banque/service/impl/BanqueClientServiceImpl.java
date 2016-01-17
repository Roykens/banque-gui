package com.douwe.banque.service.impl;

import com.douwe.banque.dao.DaoFactory;
import com.douwe.banque.dao.DataAccessException;
import com.douwe.banque.data.OperationType;
import com.douwe.banque.model.Account;
import com.douwe.banque.model.Customer;
import com.douwe.banque.model.Operation;
import com.douwe.banque.model.User;
import com.douwe.banque.service.IBanqueClientService;
import com.douwe.banque.service.exception.ServiceException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public class BanqueClientServiceImpl implements IBanqueClientService{
    private final DaoFactory daoFactory;

    public BanqueClientServiceImpl() {
        daoFactory = new DaoFactory();
    }
    
     @Override
   
 public List<Account> findAccountByCustomerId(Integer id) throws ServiceException {
        List<Account> result = new ArrayList<>();
        try {
            Customer customer = daoFactory.getCustomerDao().findById(id);
            for (Account account : daoFactory.getAccountDao().findByCustomer(customer)){
                if (account.getStatus() == 0)
                    result.add(account);
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
        return result;
    }
 
 @Override
    public void transfer(String depart, String destination, double montant, int userId) throws ServiceException {
        try {
            User user = daoFactory.getUserDao().findById(userId);
            if ((user == null) || (user.getStatus() != 0))
                throw  new ServiceException("Impossible d'effectuer l'operation");
            Account accDepart = daoFactory.getAccountDao().findByAccountNumber(depart);
            if ((accDepart == null) || (accDepart.getStatus() != 0))
                throw  new ServiceException("Le compte avec numéro "+ depart+" est introuvable");
            Account accDest = daoFactory.getAccountDao().findByAccountNumber(destination);
            if ((accDest == null) || (accDest.getStatus() != 0))
                throw  new ServiceException("Le compte avec numéro "+ destination +" est introuvable");
            if(accDepart.getBalance() < montant)
                throw  new ServiceException("Le solde du compte "+ depart +" est insuffisant pour l'opération");
            accDepart.setBalance(accDepart.getBalance() - montant);
            accDest.setBalance(accDepart.getBalance() + montant);
            daoFactory.getAccountDao().update(accDepart);
            daoFactory.getAccountDao().update(accDest);
            Operation op = new Operation();
            op.setAccount(accDepart);
            op.setDateOperation(new Date());
            op.setType(OperationType.transfer);
            op.setUser(user);
            op.setDescription(String.format("Transfert de %.2f du compte %s vers le compte %s", montant, depart, destination));
            daoFactory.getOperationDao().save(op);            
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }        
    }
    
}
