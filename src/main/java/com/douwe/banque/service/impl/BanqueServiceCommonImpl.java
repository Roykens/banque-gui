package com.douwe.banque.service.impl;


import com.douwe.banque.dao.DaoFactory;
import com.douwe.banque.dao.DataAccessException;
import com.douwe.banque.model.Customer;
import com.douwe.banque.model.Operation;
import com.douwe.banque.model.User;
import com.douwe.banque.service.IBanqueCommonService;
import com.douwe.banque.service.ServiceException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public class BanqueServiceCommonImpl implements IBanqueCommonService{
    private final DaoFactory daoFactory;

    public BanqueServiceCommonImpl() {
        daoFactory = new DaoFactory();
    }

    @Override
    public User login(String username, String passwd) throws ServiceException {
        try {
            User us = daoFactory.getUserDao().findByLogin(username);
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
            User user = daoFactory.getUserDao().findByLogin(login);
            return daoFactory.getCustomerDao().findByUser(user);
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }
    
        @Override
    public void saveOperation(Operation op) throws ServiceException {
        try {
            daoFactory.getOperationDao().save(op);
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }
    
}
