package com.douwe.banque.service;

import com.douwe.banque.data.OperationType;
import com.douwe.banque.model.Customer;
import com.douwe.banque.model.Operation;
import com.douwe.banque.model.User;
import com.douwe.banque.model.projection.AccountOperation;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public interface IBanqueCommonService {
    
    public User login(String username, String passwd) throws ServiceException;
    
    public Customer findCustomerByLogin(String login) throws ServiceException;
    
    public void saveOperation(Operation op) throws ServiceException;
    
    public List<AccountOperation> findOperationByCriteria(String accountNumber, String userName, OperationType opType, Date debut, Date fin) throws ServiceException;
    
}
