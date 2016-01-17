package com.douwe.banque.service;

import com.douwe.banque.service.exception.ServiceException;
import com.douwe.banque.model.Customer;
import com.douwe.banque.model.Operation;
import com.douwe.banque.model.User;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public interface IBanqueCommonService {
    
    public User login(String username, String passwd) throws ServiceException;
    
    public Customer findCustomerByLogin(String login) throws ServiceException;
    
    public void saveOperation(Operation op) throws ServiceException;
    
}
