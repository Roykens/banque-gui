package com.douwe.banque.service;

import com.douwe.banque.service.exception.ServiceException;
import com.douwe.banque.model.Account;
import java.util.List;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public interface IBanqueClientService {
    
    public List<Account> findAccountByCustomerId(Integer id) throws ServiceException;
    
    public void transfer(String depart, String destination, double montant, int userId) throws ServiceException;
    
}
