package com.douwe.banque.service.impl;

import com.douwe.banque.dao.IAccountDao;
import com.douwe.banque.dao.ICustomerDao;
import com.douwe.banque.dao.IOperationDao;
import com.douwe.banque.dao.IUserDao;
import com.douwe.banque.data.AccountType;
import com.douwe.banque.data.RoleType;
import com.douwe.banque.model.Account;
import com.douwe.banque.model.Customer;
import com.douwe.banque.model.User;
import com.douwe.banque.model.projection.AccountCustomer;
import com.douwe.banque.model.projection.AccountOperation;
import java.util.List;
import org.easymock.EasyMock;
import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.verify;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public class BanqueAdminServiceImplTest {

    private BanqueAdminServiceImpl service;

    private IUserDao mockUserDao;
    private IAccountDao mockAccountDao;
    private ICustomerDao mockCustomerDao;

    public BanqueAdminServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        service = new BanqueAdminServiceImpl();
        mockUserDao = createStrictMock(IUserDao.class);
        mockAccountDao = createStrictMock(IAccountDao.class);
        mockCustomerDao = createStrictMock(ICustomerDao.class);
        service.setUserDao(mockUserDao);
        service.setCustomerDao(mockCustomerDao);
        service.setAccountDao(mockAccountDao);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of findCustomerById method, of class BanqueAdminServiceImpl.
     */
    @Test
    public void testFindCustomerById() throws Exception {
        Customer customer = new Customer();
        customer.setEmailAddress("mail");
        customer.setName("royken");
        customer.setPhoneNumber("123");
        customer.setStatus(1);
        User us = new User();
        us.setLogin("royken");
        us.setPassword("admin");
        us.setRole(RoleType.ADMIN);
        us.setStatus(1);
        customer.setUser(us);
        EasyMock.expect(mockCustomerDao.findById(1)).andReturn(customer);
         EasyMock.replay(mockCustomerDao);
        Customer c = service.findCustomerById(1);
        assertNotNull(c);
        assertEquals(customer, c);
        verify(mockCustomerDao);
    }

    /**
     * Test of findUserByLogin method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testFindUserByLogin() throws Exception {
        System.out.println("findUserByLogin");
        String login = "";
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        User expResult = null;
        User result = instance.findUserByLogin(login);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAccountByNumber method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testFindAccountByNumber() throws Exception {
        System.out.println("findAccountByNumber");
        String accountNumber = "";
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        Account expResult = null;
        Account result = instance.findAccountByNumber(accountNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllUsers method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testFindAllUsers() throws Exception {
        System.out.println("findAllUsers");
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        List<User> expResult = null;
        List<User> result = instance.findAllUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveOrUpdateAccount method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testSaveOrUpdateAccount() throws Exception {
        System.out.println("saveOrUpdateAccount");
        Account account = null;
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        Account expResult = null;
        Account result = instance.saveOrUpdateAccount(account);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveOrUpdateUser method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testSaveOrUpdateUser() throws Exception {
        System.out.println("saveOrUpdateUser");
        User user = null;
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        User expResult = null;
        User result = instance.saveOrUpdateUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteUser method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testDeleteUser() throws Exception {
        System.out.println("deleteUser");
        Integer userId = null;
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        instance.deleteUser(userId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAccount method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testDeleteAccount() throws Exception {
        System.out.println("deleteAccount");
        Integer accountId = null;
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        instance.deleteAccount(accountId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of credit method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testCredit() throws Exception {
        System.out.println("credit");
        String account = "";
        double balance = 0.0;
        int userId = 0;
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        instance.credit(account, balance, userId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of debit method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testDebit() throws Exception {
        System.out.println("debit");
        String account = "";
        double balance = 0.0;
        int userId = 0;
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        instance.debit(account, balance, userId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveOrUpdateCustomer method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testSaveOrUpdateCustomer() throws Exception {
        System.out.println("saveOrUpdateCustomer");
        Customer customer = null;
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        Customer expResult = null;
        Customer result = instance.saveOrUpdateCustomer(customer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCustomer method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testDeleteCustomer() throws Exception {
        System.out.println("deleteCustomer");
        Integer customerId = null;
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        instance.deleteCustomer(customerId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllCustomer method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testFindAllCustomer() throws Exception {
        System.out.println("findAllCustomer");
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        List<Customer> expResult = null;
        List<Customer> result = instance.findAllCustomer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findCustomerByName method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testFindCustomerByName() throws Exception {
        System.out.println("findCustomerByName");
        String name = "";
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        List<Customer> expResult = null;
        List<Customer> result = instance.findCustomerByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUserByNameAndRole method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testFindUserByNameAndRole() throws Exception {
        System.out.println("findUserByNameAndRole");
        String name = "";
        RoleType type = null;
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        List<User> expResult = null;
        List<User> result = instance.findUserByNameAndRole(name, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUserById method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testFindUserById() throws Exception {
        System.out.println("findUserById");
        Integer id = null;
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        User expResult = null;
        User result = instance.findUserById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAccountById method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testFindAccountById() throws Exception {
        System.out.println("findAccountById");
        int id = 0;
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        Account expResult = null;
        Account result = instance.findAccountById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSingleCustomerByName method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testGetSingleCustomerByName() throws Exception {
        System.out.println("getSingleCustomerByName");
        String customer = "";
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        Customer expResult = null;
        Customer result = instance.getSingleCustomerByName(customer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAccountByCriteria method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testFindAccountByCriteria() throws Exception {
        System.out.println("findAccountByCriteria");
        String customerName = "";
        String accountNumber = "";
        AccountType type = null;
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        List<AccountCustomer> expResult = null;
        List<AccountCustomer> result = instance.findAccountByCriteria(customerName, accountNumber, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllOperations method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testFindAllOperations() throws Exception {
        System.out.println("findAllOperations");
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        List<AccountOperation> expResult = null;
        List<AccountOperation> result = instance.findAllOperations();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllAccountCustomer method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testFindAllAccountCustomer() throws Exception {
        System.out.println("findAllAccountCustomer");
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        List<AccountCustomer> expResult = null;
        List<AccountCustomer> result = instance.findAllAccountCustomer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAccountCustomerById method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testFindAccountCustomerById() throws Exception {
        System.out.println("findAccountCustomerById");
        int id = 0;
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        AccountCustomer expResult = null;
        AccountCustomer result = instance.findAccountCustomerById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserByAccount method, of class BanqueAdminServiceImpl.
     */
    @Test
    @Ignore
    public void testGetUserByAccount() throws Exception {
        System.out.println("getUserByAccount");
        int id = 0;
        BanqueAdminServiceImpl instance = new BanqueAdminServiceImpl();
        User expResult = null;
        User result = instance.getUserByAccount(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
