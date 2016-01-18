/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.douwe.banque.service;

import com.douwe.banque.data.AccountType;
import com.douwe.banque.data.RoleType;
import com.douwe.banque.model.Account;
import com.douwe.banque.model.Customer;
import com.douwe.banque.model.User;
import com.douwe.banque.model.projection.AccountCustomer;
import com.douwe.banque.model.projection.AccountOperation;
import com.douwe.banque.service.exception.ServiceException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dorimène pefieneré <mpdorimene@gmail.com>
 */
public class IBanqueAdminServiceTest {
    
    public IBanqueAdminServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findCustomerById method, of class IBanqueAdminService.
     */
    @Test
    public void testFindCustomerById() throws Exception {
        System.out.println("findCustomerById");
        Integer id = null;
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        Customer expResult = null;
        Customer result = instance.findCustomerById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUserByLogin method, of class IBanqueAdminService.
     */
    @Test
    public void testFindUserByLogin() throws Exception {
        System.out.println("findUserByLogin");
        String login = "";
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        User expResult = null;
        User result = instance.findUserByLogin(login);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAccountByNumber method, of class IBanqueAdminService.
     */
    @Test
    public void testFindAccountByNumber() throws Exception {
        System.out.println("findAccountByNumber");
        String accountNumber = "";
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        Account expResult = null;
        Account result = instance.findAccountByNumber(accountNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllUsers method, of class IBanqueAdminService.
     */
    @Test
    public void testFindAllUsers() throws Exception {
        System.out.println("findAllUsers");
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        List<User> expResult = null;
        List<User> result = instance.findAllUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveOrUpdateAccount method, of class IBanqueAdminService.
     */
    @Test
    public void testSaveOrUpdateAccount() throws Exception {
        System.out.println("saveOrUpdateAccount");
        Account account = null;
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        Account expResult = null;
        Account result = instance.saveOrUpdateAccount(account);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveOrUpdateUser method, of class IBanqueAdminService.
     */
    @Test
    public void testSaveOrUpdateUser() throws Exception {
        System.out.println("saveOrUpdateUser");
        User user = null;
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        User expResult = null;
        User result = instance.saveOrUpdateUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteUser method, of class IBanqueAdminService.
     */
    @Test
    public void testDeleteUser() throws Exception {
        System.out.println("deleteUser");
        Integer userId = null;
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        instance.deleteUser(userId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAccount method, of class IBanqueAdminService.
     */
    @Test
    public void testDeleteAccount() throws Exception {
        System.out.println("deleteAccount");
        Integer accountId = null;
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        instance.deleteAccount(accountId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of credit method, of class IBanqueAdminService.
     */
    @Test
    public void testCredit() throws Exception {
        System.out.println("credit");
        String account = "";
        double balance = 0.0;
        int userId = 0;
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        instance.credit(account, balance, userId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of debit method, of class IBanqueAdminService.
     */
    @Test
    public void testDebit() throws Exception {
        System.out.println("debit");
        String account = "";
        double balance = 0.0;
        int userId = 0;
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        instance.debit(account, balance, userId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveOrUpdateCustomer method, of class IBanqueAdminService.
     */
    @Test
    public void testSaveOrUpdateCustomer() throws Exception {
        System.out.println("saveOrUpdateCustomer");
        Customer customer = null;
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        Customer expResult = null;
        Customer result = instance.saveOrUpdateCustomer(customer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCustomer method, of class IBanqueAdminService.
     */
    @Test
    public void testDeleteCustomer() throws Exception {
        System.out.println("deleteCustomer");
        Integer customerId = null;
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        instance.deleteCustomer(customerId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllCustomer method, of class IBanqueAdminService.
     */
    @Test
    public void testFindAllCustomer() throws Exception {
        System.out.println("findAllCustomer");
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        List<Customer> expResult = null;
        List<Customer> result = instance.findAllCustomer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findCustomerByName method, of class IBanqueAdminService.
     */
    @Test
    public void testFindCustomerByName() throws Exception {
        System.out.println("findCustomerByName");
        String name = "";
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        List<Customer> expResult = null;
        List<Customer> result = instance.findCustomerByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUserByNameAndRole method, of class IBanqueAdminService.
     */
    @Test
    public void testFindUserByNameAndRole() throws Exception {
        System.out.println("findUserByNameAndRole");
        String name = "";
        RoleType type = null;
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        List<User> expResult = null;
        List<User> result = instance.findUserByNameAndRole(name, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAccountByCriteria method, of class IBanqueAdminService.
     */
    @Test
    public void testFindAccountByCriteria() throws Exception {
        System.out.println("findAccountByCriteria");
        String customerName = "";
        String accountNumber = "";
        AccountType type = null;
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        List<AccountCustomer> expResult = null;
        List<AccountCustomer> result = instance.findAccountByCriteria(customerName, accountNumber, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUserById method, of class IBanqueAdminService.
     */
    @Test
    public void testFindUserById() throws Exception {
        System.out.println("findUserById");
        Integer integer = null;
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        User expResult = null;
        User result = instance.findUserById(integer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllOperations method, of class IBanqueAdminService.
     */
    @Test
    public void testFindAllOperations() throws Exception {
        System.out.println("findAllOperations");
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        List<AccountOperation> expResult = null;
        List<AccountOperation> result = instance.findAllOperations();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllAccountCustomer method, of class IBanqueAdminService.
     */
    @Test
    public void testFindAllAccountCustomer() throws Exception {
        System.out.println("findAllAccountCustomer");
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        List<AccountCustomer> expResult = null;
        List<AccountCustomer> result = instance.findAllAccountCustomer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAccountCustomerById method, of class IBanqueAdminService.
     */
    @Test
    public void testFindAccountCustomerById() throws Exception {
        System.out.println("findAccountCustomerById");
        int id = 0;
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        AccountCustomer expResult = null;
        AccountCustomer result = instance.findAccountCustomerById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAccountById method, of class IBanqueAdminService.
     */
    @Test
    public void testFindAccountById() throws Exception {
        System.out.println("findAccountById");
        int id = 0;
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        Account expResult = null;
        Account result = instance.findAccountById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSingleCustomerByName method, of class IBanqueAdminService.
     */
    @Test
    public void testGetSingleCustomerByName() throws Exception {
        System.out.println("getSingleCustomerByName");
        String customer = "";
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        Customer expResult = null;
        Customer result = instance.getSingleCustomerByName(customer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserByAccount method, of class IBanqueAdminService.
     */
    @Test
    public void testGetUserByAccount() throws Exception {
        System.out.println("getUserByAccount");
        int id = 0;
        IBanqueAdminService instance = new IBanqueAdminServiceImpl();
        User expResult = null;
        User result = instance.getUserByAccount(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IBanqueAdminServiceImpl implements IBanqueAdminService {

        public Customer findCustomerById(Integer id) throws ServiceException {
            return null;
        }

        public User findUserByLogin(String login) throws ServiceException {
            return null;
        }

        public Account findAccountByNumber(String accountNumber) throws ServiceException {
            return null;
        }

        public List<User> findAllUsers() throws ServiceException {
            return null;
        }

        public Account saveOrUpdateAccount(Account account) throws ServiceException {
            return null;
        }

        public User saveOrUpdateUser(User user) throws ServiceException {
            return null;
        }

        public void deleteUser(Integer userId) throws ServiceException {
        }

        public void deleteAccount(Integer accountId) throws ServiceException {
        }

        public void credit(String account, double balance, int userId) throws ServiceException {
        }

        public void debit(String account, double balance, int userId) throws ServiceException {
        }

        public Customer saveOrUpdateCustomer(Customer customer) throws ServiceException {
            return null;
        }

        public void deleteCustomer(Integer customerId) throws ServiceException {
        }

        public List<Customer> findAllCustomer() throws ServiceException {
            return null;
        }

        public List<Customer> findCustomerByName(String name) throws ServiceException {
            return null;
        }

        public List<User> findUserByNameAndRole(String name, RoleType type) throws ServiceException {
            return null;
        }

        public List<AccountCustomer> findAccountByCriteria(String customerName, String accountNumber, AccountType type) throws ServiceException {
            return null;
        }

        public User findUserById(Integer integer) throws ServiceException {
            return null;
        }

        public List<AccountOperation> findAllOperations() throws ServiceException {
            return null;
        }

        public List<AccountCustomer> findAllAccountCustomer() throws ServiceException {
            return null;
        }

        public AccountCustomer findAccountCustomerById(int id) throws ServiceException {
            return null;
        }

        public Account findAccountById(int id) throws ServiceException {
            return null;
        }

        public Customer getSingleCustomerByName(String customer) throws ServiceException {
            return null;
        }

        public User getUserByAccount(int id) throws ServiceException {
            return null;
        }
    }
    
}
