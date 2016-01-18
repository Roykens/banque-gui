/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.douwe.banque.dao;

import com.douwe.banque.model.Account;
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
public class IUserDaoTest {
    
    public IUserDaoTest() {
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
     * Test of save method, of class IUserDao.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        User user = null;
        IUserDao instance = new IUserDaoImpl();
        User expResult = null;
        User result = instance.save(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class IUserDao.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        User user = null;
        IUserDao instance = new IUserDaoImpl();
        instance.delete(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class IUserDao.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        User user = null;
        IUserDao instance = new IUserDaoImpl();
        User expResult = null;
        User result = instance.update(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class IUserDao.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        Integer id = null;
        IUserDao instance = new IUserDaoImpl();
        User expResult = null;
        User result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class IUserDao.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        IUserDao instance = new IUserDaoImpl();
        List<User> expResult = null;
        List<User> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByLogin method, of class IUserDao.
     */
    @Test
    public void testFindByLogin() throws Exception {
        System.out.println("findByLogin");
        String login = "";
        IUserDao instance = new IUserDaoImpl();
        User expResult = null;
        User result = instance.findByLogin(login);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByStatus method, of class IUserDao.
     */
    @Test
    public void testFindByStatus() throws Exception {
        System.out.println("findByStatus");
        int status = 0;
        IUserDao instance = new IUserDaoImpl();
        List<User> expResult = null;
        List<User> result = instance.findByStatus(status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUserByAccount method, of class IUserDao.
     */
    @Test
    public void testFindUserByAccount() throws Exception {
        System.out.println("findUserByAccount");
        Account account = null;
        IUserDao instance = new IUserDaoImpl();
        User expResult = null;
        User result = instance.findUserByAccount(account);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IUserDaoImpl implements IUserDao {

        public User save(User user) throws DataAccessException {
            return null;
        }

        public void delete(User user) throws DataAccessException {
        }

        public User update(User user) throws DataAccessException {
            return null;
        }

        public User findById(Integer id) throws DataAccessException {
            return null;
        }

        public List<User> findAll() throws DataAccessException {
            return null;
        }

        public User findByLogin(String login) throws DataAccessException {
            return null;
        }

        public List<User> findByStatus(int status) throws DataAccessException {
            return null;
        }

        public User findUserByAccount(Account account) throws DataAccessException {
            return null;
        }
    }
    
}
