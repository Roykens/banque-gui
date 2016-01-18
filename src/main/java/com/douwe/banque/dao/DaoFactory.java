package com.douwe.banque.dao;

import com.douwe.banque.dao.jdbcimpl.AccountDaoJDBC;
import com.douwe.banque.dao.jdbcimpl.CustomerDaoJDBC;
import com.douwe.banque.dao.jdbcimpl.OperationDaoJDBC;
import com.douwe.banque.dao.jdbcimpl.UserDaoJDBC;



/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public class DaoFactory {
    private IAccountDao accountDao;
    
    private ICustomerDao customerDao;
    
    private IOperationDao operationDao;
    
    private IUserDao userDao;
    
    public DaoFactory(){
        accountDao = new AccountDaoJDBC();
        customerDao = new CustomerDaoJDBC();
        operationDao = new OperationDaoJDBC();
        userDao = new UserDaoJDBC();
    }

    public IAccountDao getAccountDao() {
        return accountDao;
    }

    public ICustomerDao getCustomerDao() {
        return customerDao;
    }

    public IOperationDao getOperationDao() {
        return operationDao;
    }

    public IUserDao getUserDao() {
        return userDao;
    }
}
