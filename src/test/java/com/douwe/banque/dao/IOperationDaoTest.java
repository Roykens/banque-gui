/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.douwe.banque.dao;

import com.douwe.banque.model.Customer;
import com.douwe.banque.model.Operation;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @@author dorimène pefieneré <mpdorimene@gmail.com>
 */
public class IOperationDaoTest {
    
    public IOperationDaoTest() {
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
     * Test of save method, of class IOperationDao.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Operation operation = null;
        IOperationDao instance = new IOperationDaoImpl();
        Operation expResult = null;
        Operation result = instance.save(operation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class IOperationDao.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Operation operation = null;
        IOperationDao instance = new IOperationDaoImpl();
        instance.delete(operation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class IOperationDao.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Operation operation = null;
        IOperationDao instance = new IOperationDaoImpl();
        Operation expResult = null;
        Operation result = instance.update(operation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class IOperationDao.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        Integer id = null;
        IOperationDao instance = new IOperationDaoImpl();
        Operation expResult = null;
        Operation result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class IOperationDao.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        IOperationDao instance = new IOperationDaoImpl();
        List<Operation> expResult = null;
        List<Operation> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findForCustomer method, of class IOperationDao.
     */
    @Test
    public void testFindForCustomer() throws Exception {
        System.out.println("findForCustomer");
        Customer customer = null;
        IOperationDao instance = new IOperationDaoImpl();
        List<Operation> expResult = null;
        List<Operation> result = instance.findForCustomer(customer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IOperationDaoImpl implements IOperationDao {

        public Operation save(Operation operation) throws DataAccessException {
            return null;
        }

        public void delete(Operation operation) throws DataAccessException {
        }

        public Operation update(Operation operation) throws DataAccessException {
            return null;
        }

        public Operation findById(Integer id) throws DataAccessException {
            return null;
        }

        public List<Operation> findAll() throws DataAccessException {
            return null;
        }

        public List<Operation> findForCustomer(Customer customer) throws DataAccessException {
            return null;
        }
    }
    
}
