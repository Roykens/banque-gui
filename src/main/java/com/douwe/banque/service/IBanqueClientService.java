package com.douwe.banque.service;

import com.douwe.banque.model.Account;
import com.douwe.banque.model.projection.AccountOperation;
import java.util.List;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public interface IBanqueClientService {
    
    public List<Account> findAccountByCustomerId(Integer id) throws ServiceException;
    
    public void transfer(String depart, String destination, double montant, int userId) throws ServiceException;
    
    public List<AccountOperation> findOperationFromCustomerAccounts(int customerId) throws ServiceException;
    
}
