package com.douwe.banque.service.impl;

import com.douwe.banque.dao.DaoFactory;
import com.douwe.banque.dao.DataAccessException;
import com.douwe.banque.dao.IAccountDao;
import com.douwe.banque.dao.ICustomerDao;
import com.douwe.banque.dao.IUserDao;
import com.douwe.banque.data.AccountType;
import com.douwe.banque.data.OperationType;
import com.douwe.banque.data.RoleType;
import com.douwe.banque.model.Account;
import com.douwe.banque.model.Customer;
import com.douwe.banque.model.Operation;
import com.douwe.banque.model.User;
import com.douwe.banque.model.projection.AccountCustomer;
import com.douwe.banque.model.projection.AccountOperation;
import com.douwe.banque.service.IBanqueAdminService;
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
public class BanqueAdminServiceImpl implements IBanqueAdminService{
    private final DaoFactory daoFactory;

    public BanqueAdminServiceImpl() {
        daoFactory = new DaoFactory();
    }
    
    @Override
    public Customer findCustomerById(Integer id) throws ServiceException {
        try {
            return daoFactory.getCustomerDao().findById(id);
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }
    

    
     @Override
    public User findUserByLogin(String login) throws ServiceException {
        try {
            return daoFactory.getUserDao().findByLogin(login);
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }
    

    
    @Override
    public Account findAccountByNumber(String accountNumber) throws ServiceException {
        try {
            return daoFactory.getAccountDao().findByAccountNumber(accountNumber);
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }
    
    @Override
    public List<User> findAllUsers() throws ServiceException {
        try {
            return daoFactory.getUserDao().findByStatus(0);
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }
    
    @Override
    public Account saveOrUpdateAccount(Account account) throws ServiceException {
        try {
            if (account.getId() == null) {
                return daoFactory.getAccountDao().save(account);
            } else {
                return daoFactory.getAccountDao().update(account);
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }
    
    @Override
    public User saveOrUpdateUser(User user) throws ServiceException {
        try {
            if (user.getId() == null) {
                return daoFactory.getUserDao().save(user);
            } else {
                return daoFactory.getUserDao().update(user);
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }
    
    @Override
    public void deleteUser(Integer userId) throws ServiceException {
        try {
            IUserDao userDao = daoFactory.getUserDao();
            User user = userDao.findById(userId);
            if (user == null) {
                throw new ServiceException("L'utilisateur ayant pour id " + userId + " n'a pas été trouvé");
            }
            user.setStatus(1);
            userDao.update(user);
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }
    
      @Override
    public void deleteAccount(Integer accountId) throws ServiceException {
        try {
            IAccountDao accountDao = daoFactory.getAccountDao();
            Account account = accountDao.findById(accountId);
            if (account == null) {
                throw new ServiceException("Le compte ayant pour id " + accountId + " n'a pas été trouvé");
            }
            account.setStatus(1);
            accountDao.update(account);
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }
    
    @Override
    public void credit(String account, double balance, int userId) throws ServiceException {
        try {
            User user = daoFactory.getUserDao().findById(userId);
            if ((user == null) || (user.getStatus() != 0))
                throw  new ServiceException("Operation impossible à effectuer");
            Account accDepart = daoFactory.getAccountDao().findByAccountNumber(account);
            if ((accDepart == null) || (accDepart.getStatus() != 0))
                throw  new ServiceException("Le compte avec numéro "+ account+" est introuvable");
            accDepart.setBalance(accDepart.getBalance() + balance);
            daoFactory.getAccountDao().update(accDepart);
            Operation op = new Operation();
            op.setAccount(accDepart);
            op.setDateOperation(new Date());
            op.setType(OperationType.CREDIT);
            op.setUser(user);
            op.setDescription(String.format("Credit du compte %s de %.2f", account, balance));
            daoFactory.getOperationDao().save(op);            
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }
    
    @Override
    public void debit(String account, double balance, int userId) throws ServiceException {
        try {
            User user = daoFactory.getUserDao().findById(userId);
            if ((user == null) || (user.getStatus() != 0))
                throw  new ServiceException("Impossible d'effectuer l'operation");
            Account accDepart = daoFactory.getAccountDao().findByAccountNumber(account);
            if ((accDepart == null) || (accDepart.getStatus() != 0))
                throw  new ServiceException("Le compte avec numéro "+ account+" est introuvable");
            if(accDepart.getBalance() < balance)
                throw  new ServiceException("Le solde du compte "+ account + " est insuffisant pour effectuer l'opération");
            accDepart.setBalance(accDepart.getBalance() - balance);
            daoFactory.getAccountDao().update(accDepart);
            Operation op = new Operation();
            op.setAccount(accDepart);
            op.setDateOperation(new Date());
            op.setType(OperationType.DEBIT);
            op.setUser(user);
            op.setDescription(String.format("Debit du compte %s de %.2f", account, balance));
            daoFactory.getOperationDao().save(op);            
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }
    
    @Override
    public Customer saveOrUpdateCustomer(Customer customer) throws ServiceException {
        try {
            if (customer.getId() == null) {
                String nn = customer.getName().replaceAll(" ", "").toLowerCase();
                User user = new User();
                user.setLogin(nn);
                user.setPassword("admin");
                user.setRole(RoleType.CUSTOMER);
                user.setStatus(0);
                user = daoFactory.getUserDao().save(user);
                customer.setUser(user);
                return daoFactory.getCustomerDao().save(customer);
            } else {
                return daoFactory.getCustomerDao().update(customer);
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }
    
    @Override
    public void deleteCustomer(Integer customerId) throws ServiceException {
        try {
            ICustomerDao customerDao = daoFactory.getCustomerDao();
            Customer customer = customerDao.findById(customerId);
            if (customer == null) {
                throw new ServiceException("Customer with id " + customerId + " not found");
            }
            customer.setStatus(1);
            customerDao.update(customer);
            User u = customer.getUser();
            u.setStatus(1);
            daoFactory.getUserDao().update(u);
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }
    
    @Override
    public List<Customer> findAllCustomer() throws ServiceException {
        List<Customer> result = new ArrayList<>();
        try {
            for (Customer customer : daoFactory.getCustomerDao().findAll()) {
                if (customer.getStatus() == 0) {
                    result.add(customer);
                }
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
        return result;
    }
    
    @Override
    public List<Customer> findCustomerByName(String name) throws ServiceException {
        try {
            List<Customer> customers = new ArrayList<>();
            for (Customer customer : daoFactory.getCustomerDao().findAll()) {
                if ((customer.getName().toLowerCase().contains(name.toLowerCase())) && (customer.getStatus() == 0)) {
                    customers.add(customer);
                }
            }
            return customers;
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }
    
    @Override
    public List<User> findUserByNameAndRole(String name, RoleType type) throws ServiceException {
        try {
            List<User> users = new ArrayList<>();
            for (User user : daoFactory.getUserDao().findAll()) {
                if ((name.isEmpty() || (user.getLogin().toLowerCase().contains(name.toLowerCase()))) && ((type == null) || (user.getRole() == type)) && (user.getStatus() == 0)) {
                    users.add(user);
                }
            }
            return users;
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }
    
    @Override
    public User findUserById(Integer id) throws ServiceException {
        try {
            return daoFactory.getUserDao().findById(id);
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }
    
    @Override
    public Account findAccountById(int id) throws ServiceException {
        try {
            Account a = daoFactory.getAccountDao().findById(id);
            if ((a == null) || (a.getStatus() != 0)) {
                return null;
            }
            return a;
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
    }
    
    @Override
    public Customer getSingleCustomerByName(String customer) throws ServiceException {        
        try {            
            Customer cust = daoFactory.getCustomerDao().findByName(customer);
            if ((cust != null) && (cust.getStatus() != 0))
                    return null;
            return cust;
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }

    }

    @Override
    public List<AccountCustomer> findAccountByCriteria(String customerName, String accountNumber, AccountType type) throws ServiceException {
        List<AccountCustomer> result = new ArrayList<>();
        try {
            for (Account account : daoFactory.getAccountDao().findAll()) {
                if ((customerName.isEmpty() || (account.getCustomer().getName().toLowerCase().contains(customerName.toLowerCase())))
                        && (accountNumber.isEmpty() || (account.getAccountNumber().toLowerCase().contains(accountNumber.toLowerCase())))
                        && ((type == null) || (account.getType() == type))
                        && (account.getStatus() == 0)) {
                    result.add(new AccountCustomer(account.getCustomer().getName(), account));
                }
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
        return result;
    }

    

    @Override
    public List<AccountOperation> findAllOperations() throws ServiceException {
        List<AccountOperation> result = new ArrayList<>();
        try {            
            for (Operation operation : daoFactory.getOperationDao().findAll()) {
                result.add(new AccountOperation(operation.getUser().getLogin(), operation.getAccount().getAccountNumber(), operation));
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
        return result;
    }

    @Override
    public List<AccountCustomer> findAllAccountCustomer() throws ServiceException {
        List<AccountCustomer> result = new ArrayList<>();
        try {
            List<Account> accounts = daoFactory.getAccountDao().findAll();
            for (Account account : accounts) {
                if (account.getStatus() == 0) {
                    AccountCustomer a = new AccountCustomer(account.getCustomer().getName(), account);
                    result.add(a);
                }
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
        return result;
    }

    @Override
    public AccountCustomer findAccountCustomerById(int id) throws ServiceException {
        try {
            Account acc = daoFactory.getAccountDao().findById(id);
            if (acc != null) {
                return new AccountCustomer(acc.getCustomer().getName(), acc);
            }
        } catch (DataAccessException ex) {
            Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex);
        }
        return null;
    }

    @Override
    public User getUserByAccount(int id) throws ServiceException {
        User user = null;
        Account account = this.findAccountById(id);
        if(account != null){
            try {
                return daoFactory.getUserDao().findUserByAccount(account);
            } catch (DataAccessException ex) {
                Logger.getLogger(BanqueAdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    
    
}
