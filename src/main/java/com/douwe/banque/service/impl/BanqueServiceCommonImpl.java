package com.douwe.banque.service.impl;


import com.douwe.banque.dao.DaoFactory;
import com.douwe.banque.dao.DataAccessException;
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
    private DaoFactory daoFactory;

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
    
}
