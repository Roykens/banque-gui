/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.douwe.banque.dao;

import com.douwe.banque.model.Account;
import com.douwe.banque.model.Customer;
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
public class IAccountDaoTest {
    
    public IAccountDaoTest() {
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
     * Test of save method, of class IAccountDao.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Account account = null;
        IAccountDao instance = new IAccountDaoImpl();
        Account expResult = null;
        Account result = instance.save(account);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class IAccountDao.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Account account = null;
        IAccountDao instance = new IAccountDaoImpl();
        instance.delete(account);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class IAccountDao.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Account account = null;
        IAccountDao instance = new IAccountDaoImpl();
        Account expResult = null;
        Account result = instance.update(account);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class IAccountDao.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        IAccountDao instance = new IAccountDaoImpl();
        List<Account> expResult = null;
        List<Account> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class IAccountDao.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        Integer id = null;
        IAccountDao instance = new IAccountDaoImpl();
        Account expResult = null;
        Account result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByAccountNumber method, of class IAccountDao.
     */
    @Test
    public void testFindByAccountNumber() throws Exception {
        System.out.println("findByAccountNumber");
        String accountNumber = "";
        IAccountDao instance = new IAccountDaoImpl();
        Account expResult = null;
        Account result = instance.findByAccountNumber(accountNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByCustomer method, of class IAccountDao.
     */
    @Test
    public void testFindByCustomer() throws Exception {
        System.out.println("findByCustomer");
        Customer customer = null;
        IAccountDao instance = new IAccountDaoImpl();
        List<Account> expResult = null;
        List<Account> result = instance.findByCustomer(customer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IAccountDaoImpl implements IAccountDao {

        public Account save(Account account) throws DataAccessException {
            return null;
        }

        public void delete(Account account) throws DataAccessException {
        }

        public Account update(Account account) throws DataAccessException {
            return null;
        }

        public List<Account> findAll() throws DataAccessException {
            return null;
        }

        public Account findById(Integer id) throws DataAccessException {
            return null;
        }

        public Account findByAccountNumber(String accountNumber) throws DataAccessException {
            return null;
        }

        public List<Account> findByCustomer(Customer customer) throws DataAccessException {
            return null;
        }
    }
    
}
