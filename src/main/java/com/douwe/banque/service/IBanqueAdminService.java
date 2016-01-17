package com.douwe.banque.service;

import com.douwe.banque.service.exception.ServiceException;
import com.douwe.banque.data.AccountType;
import com.douwe.banque.data.OperationType;
import com.douwe.banque.data.RoleType;
import com.douwe.banque.model.Account;
import com.douwe.banque.model.Customer;
import com.douwe.banque.model.Operation;
import com.douwe.banque.model.User;
import com.douwe.banque.model.projection.AccountCustomer;
import com.douwe.banque.model.projection.AccountOperation;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public interface IBanqueAdminService {

    public Customer findCustomerById(Integer id) throws ServiceException;

    public User findUserByLogin(String login) throws ServiceException;

    public Account findAccountByNumber(String accountNumber) throws ServiceException;

    public List<User> findAllUsers() throws ServiceException;

    public Account saveOrUpdateAccount(Account account) throws ServiceException;

    public User saveOrUpdateUser(User user) throws ServiceException;

    public void deleteUser(Integer userId) throws ServiceException;

    public void deleteAccount(Integer accountId) throws ServiceException;

    public void credit(String account, double balance, int userId) throws ServiceException;

    public void debit(String account, double balance, int userId) throws ServiceException;

    public Customer saveOrUpdateCustomer(Customer customer) throws ServiceException;

    public void deleteCustomer(Integer customerId) throws ServiceException;

    public List<Customer> findAllCustomer() throws ServiceException;

    public List<Customer> findCustomerByName(String name) throws ServiceException;

    public List<User> findUserByNameAndRole(String name, RoleType type) throws ServiceException;

    public List<AccountCustomer> findAccountByCriteria(String customerName, String accountNumber, AccountType type) throws ServiceException;

    public User findUserById(Integer integer) throws ServiceException;

    public List<AccountOperation> findAllOperations() throws ServiceException;

    public List<AccountCustomer> findAllAccountCustomer() throws ServiceException;

    public AccountCustomer findAccountCustomerById(int id) throws ServiceException;

    public Account findAccountById(int id) throws ServiceException;

    public Customer getSingleCustomerByName(String customer) throws ServiceException;
    
    public User getUserByAccount(int id) throws ServiceException;

}
