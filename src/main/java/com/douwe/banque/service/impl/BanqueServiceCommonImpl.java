package com.douwe.banque.service.impl;

import com.douwe.banque.dao.DaoFactory;
import com.douwe.banque.dao.DataAccessException;
import com.douwe.banque.dao.ICustomerDao;
import com.douwe.banque.dao.IOperationDao;
import com.douwe.banque.dao.IUserDao;
import com.douwe.banque.dao.jdbcImpl.CustomerDaoJDBC;
import com.douwe.banque.dao.jdbcImpl.OperationDaoJDBC;
import com.douwe.banque.dao.jdbcImpl.UserDaoJDBC;
import com.douwe.banque.data.OperationType;
import com.douwe.banque.model.Customer;
import com.douwe.banque.model.Operation;
import com.douwe.banque.model.User;
import com.douwe.banque.model.projection.AccountOperation;
import com.douwe.banque.service.IBanqueCommonService;

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
public class BanqueServiceCommonImpl implements IBanqueCommonService {

    private IUserDao userDao;
    private IOperationDao operationDao;
    private ICustomerDao customerDao;

    public BanqueServiceCommonImpl() {
        userDao = new UserDaoJDBC();
        operationDao = new OperationDaoJDBC();
        customerDao = new CustomerDaoJDBC();
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public void setOperationDao(IOperationDao operationDao) {
        this.operationDao = operationDao;
    }

    public void setCustomerDao(ICustomerDao customerDao) {
        this.customerDao = customerDao;
    }
    
    

    @Override
    public User login(String username, String passwd) throws ServiceException {
        try {
            User us = userDao.findByLogin(username);
            if ((us != null) && (us.getPassword().equalsIgnoreCase(passwd))) {
                return us;
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueServiceCommonImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
        return null;
    }

    @Override
    public Customer findCustomerByLogin(String login) throws ServiceException {
        try {
            User user = userDao.findByLogin(login);
            return customerDao.findByUser(user);
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public void saveOperation(Operation op) throws ServiceException {
        try {
            operationDao.save(op);
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<AccountOperation> findOperationByCriteria(String accountNumber, String userName, OperationType opType, Date debut, Date fin) throws ServiceException {
        List<AccountOperation> result = new ArrayList<>();
        try {
            for (Operation operation : operationDao.findAll()) {
                if ((userName.isEmpty() || operation.getUser().getLogin().toLowerCase().contains(userName.toLowerCase()))
                        && (accountNumber.isEmpty() || operation.getAccount().getAccountNumber().toLowerCase().contains(accountNumber.toLowerCase()))
                        && ((opType == null) || operation.getType() == opType)
                        && ((debut == null) || operation.getDateOperation().after(debut))
                        && ((fin == null) || operation.getDateOperation().before(fin))) {
                    result.add(new AccountOperation(operation.getUser().getLogin(), operation.getAccount().getAccountNumber(), operation));
                }
            }

        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
        return result;
    }

}
