package com.douwe.banque.service;

import com.douwe.banque.model.User;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public interface IBanqueCommonService {
    
    public User login(String username, String passwd) throws ServiceException;
    
}
