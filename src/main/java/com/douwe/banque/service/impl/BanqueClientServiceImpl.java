package com.douwe.banque.service.impl;

import com.douwe.banque.dao.DaoFactory;
import com.douwe.banque.dao.DataAccessException;
import com.douwe.banque.dao.IAccountDao;
import com.douwe.banque.dao.ICustomerDao;
import com.douwe.banque.dao.IOperationDao;
import com.douwe.banque.dao.IUserDao;
import com.douwe.banque.dao.jdbcImpl.AccountDaoJDBC;
import com.douwe.banque.dao.jdbcImpl.CustomerDaoJDBC;
import com.douwe.banque.dao.jdbcImpl.OperationDaoJDBC;
import com.douwe.banque.dao.jdbcImpl.UserDaoJDBC;
import com.douwe.banque.data.OperationType;
import com.douwe.banque.model.Account;
import com.douwe.banque.model.Customer;
import com.douwe.banque.model.Operation;
import com.douwe.banque.model.User;
import com.douwe.banque.model.projection.AccountOperation;
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

    private ICustomerDao customerDao;
    private IAccountDao accountDao;
    private IOperationDao operationDao;
    private IUserDao userDao;

    public BanqueClientServiceImpl() {
        customerDao = new CustomerDaoJDBC();
        accountDao = new AccountDaoJDBC();
        operationDao = new OperationDaoJDBC();
        userDao = new UserDaoJDBC();
        
    }

    public void setCustomerDao(ICustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setOperationDao(IOperationDao operationDao) {
        this.operationDao = operationDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
    
    
    
     @Override
   
 public List<Account> findAccountByCustomerId(Integer id) throws ServiceException {
        List<Account> result = new ArrayList<>();
        try {
            Customer customer = customerDao.findById(id);
            for (Account account : accountDao.findByCustomer(customer)){
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
            User user = userDao.findById(userId);
            if ((user == null) || (user.getStatus() != 0))
                throw  new ServiceException("Impossible d'effectuer l'operation");
            Account accDepart = accountDao.findByAccountNumber(depart);
            if ((accDepart == null) || (accDepart.getStatus() != 0))
                throw  new ServiceException("Le compte avec numéro "+ depart+" est introuvable");
            Account accDest = accountDao.findByAccountNumber(destination);
            if ((accDest == null) || (accDest.getStatus() != 0))
                throw  new ServiceException("Le compte avec numéro "+ destination +" est introuvable");
            if(accDepart.getBalance() < montant)
                throw  new ServiceException("Le solde du compte "+ depart +" est insuffisant pour l'opération");
            accDepart.setBalance(accDepart.getBalance() - montant);
            accDest.setBalance(accDepart.getBalance() + montant);
            accountDao.update(accDepart);
            accountDao.update(accDest);
            Operation op = new Operation();
            op.setAccount(accDepart);
            op.setDateOperation(new Date());
            op.setType(OperationType.transfer);
            op.setUser(user);
            op.setDescription(String.format("Transfert de %.2f du compte %s vers le compte %s", montant, depart, destination));
            operationDao.save(op);            
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }        
    }
    
        @Override
    public List<AccountOperation> findOperationFromCustomerAccounts(int customerId) throws ServiceException {
         List<AccountOperation> result = new ArrayList<>();
        try {  
            Customer customer = customerDao.findById(customerId);
            List<Operation> ops = operationDao.findForCustomer(customer);        
            for (Operation operation : ops) {
                result.add(new AccountOperation(operation.getUser().getLogin(), operation.getAccount().getAccountNumber(), operation));
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
        return result;
    }

    
}
