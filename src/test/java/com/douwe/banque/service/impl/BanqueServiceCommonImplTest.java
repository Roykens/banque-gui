package com.douwe.banque.service.impl;

import com.douwe.banque.dao.ICustomerDao;
import com.douwe.banque.dao.IOperationDao;
import com.douwe.banque.dao.IUserDao;
import com.douwe.banque.data.OperationType;
import com.douwe.banque.data.RoleType;
import com.douwe.banque.model.Customer;
import com.douwe.banque.model.Operation;
import com.douwe.banque.model.User;
import com.douwe.banque.model.projection.AccountOperation;
import java.util.Date;
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
public class BanqueServiceCommonImplTest {

    private BanqueServiceCommonImpl service;
    private IUserDao mockUserDao;
    private ICustomerDao mockCustomerDao;

    public BanqueServiceCommonImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        service = new BanqueServiceCommonImpl();
        mockUserDao = createStrictMock(IUserDao.class);
        mockCustomerDao = createStrictMock(ICustomerDao.class);
        service.setUserDao(mockUserDao);
        service.setCustomerDao(mockCustomerDao);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of login method, of class BanqueServiceCommonImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        String username = "admin";
        String passwd = "admin";
        User user = new User();
        user.setLogin(username);
        user.setPassword(passwd);
        EasyMock.expect(mockUserDao.findByLogin(username)).andReturn(user);
        EasyMock.replay(mockUserDao);
        User result = service.login(username, passwd);
        assertNotNull(result);
        assertEquals(user, result);
        verify(mockUserDao);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of findCustomerByLogin method, of class BanqueServiceCommonImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindCustomerByLogin() throws Exception {
        System.out.println("findCustomerByLogin");
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
        EasyMock.expect(mockUserDao.findByLogin("royken")).andReturn(us);
        EasyMock.expect(mockCustomerDao.findByUser(us)).andReturn(customer);
        EasyMock.replay(mockUserDao);
        EasyMock.replay(mockCustomerDao);
        Customer c = service.findCustomerByLogin("royken");
        assertNotNull(c);
        assertEquals(customer, c);
        verify(mockCustomerDao);
    }

    /**
     * Test of saveOperation method, of class BanqueServiceCommonImpl.
     */
    @Test
    @Ignore
    public void testSaveOperation() throws Exception {
        System.out.println("saveOperation");
        Operation op = null;
        BanqueServiceCommonImpl instance = new BanqueServiceCommonImpl();
        instance.saveOperation(op);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findOperationByCriteria method, of class BanqueServiceCommonImpl.
     */
    @Test
    @Ignore
    public void testFindOperationByCriteria() throws Exception {
        System.out.println("findOperationByCriteria");
        String accountNumber = "";
        String userName = "";
        OperationType opType = null;
        Date debut = null;
        Date fin = null;
        BanqueServiceCommonImpl instance = new BanqueServiceCommonImpl();
        List<AccountOperation> expResult = null;
        List<AccountOperation> result = instance.findOperationByCriteria(accountNumber, userName, opType, debut, fin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
