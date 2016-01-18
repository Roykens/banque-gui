/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.douwe.banque.dao;

import com.douwe.banque.model.Account;
import com.douwe.banque.model.Customer;
import com.douwe.banque.model.User;
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
public class ICustomerDaoTest {
    
    public ICustomerDaoTest() {
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
     * Test of save method, of class ICustomerDao.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Customer customer = null;
        ICustomerDao instance = new ICustomerDaoImpl();
        Customer expResult = null;
        Customer result = instance.save(customer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class ICustomerDao.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Customer customer = null;
        ICustomerDao instance = new ICustomerDaoImpl();
        instance.delete(customer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class ICustomerDao.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Customer customer = null;
        ICustomerDao instance = new ICustomerDaoImpl();
        Customer expResult = null;
        Customer result = instance.update(customer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class ICustomerDao.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        Integer id = null;
        ICustomerDao instance = new ICustomerDaoImpl();
        Customer expResult = null;
        Customer result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class ICustomerDao.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        ICustomerDao instance = new ICustomerDaoImpl();
        List<Customer> expResult = null;
        List<Customer> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByUser method, of class ICustomerDao.
     */
    @Test
    public void testFindByUser() throws Exception {
        System.out.println("findByUser");
        User user = null;
        ICustomerDao instance = new ICustomerDaoImpl();
        Customer expResult = null;
        Customer result = instance.findByUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class ICustomerDao.
     */
    @Test
    public void testFindByName() throws Exception {
        System.out.println("findByName");
        String name = "";
        ICustomerDao instance = new ICustomerDaoImpl();
        Customer expResult = null;
        Customer result = instance.findByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByAccount method, of class ICustomerDao.
     */
    @Test
    public void testFindByAccount() throws Exception {
        System.out.println("findByAccount");
        Account account = null;
        ICustomerDao instance = new ICustomerDaoImpl();
        Customer expResult = null;
        Customer result = instance.findByAccount(account);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ICustomerDaoImpl implements ICustomerDao {

        public Customer save(Customer customer) throws DataAccessException {
            return null;
        }

        public void delete(Customer customer) throws DataAccessException {
        }

        public Customer update(Customer customer) throws DataAccessException {
            return null;
        }

        public Customer findById(Integer id) throws DataAccessException {
            return null;
        }

        public List<Customer> findAll() throws DataAccessException {
            return null;
        }

        public Customer findByUser(User user) throws DataAccessException {
            return null;
        }

        public Customer findByName(String name) throws DataAccessException {
            return null;
        }

        public Customer findByAccount(Account account) throws DataAccessException {
            return null;
        }
    }
    
}
