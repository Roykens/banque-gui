package com.douwe.banque.service;

import com.douwe.banque.data.OperationType;
import com.douwe.banque.data.RoleType;
import com.douwe.banque.model.Customer;
import com.douwe.banque.model.Operation;
import com.douwe.banque.model.User;
import com.douwe.banque.model.projection.AccountOperation;
import com.douwe.banque.service.exception.ServiceException;
import com.douwe.banque.service.impl.BanqueServiceCommonImpl;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public class IBanqueCommonServiceTest {
    
    private IBanqueCommonService service;
    
    public IBanqueCommonServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IBanqueCommonServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        service = new BanqueServiceCommonImpl();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of login method, of class IBanqueCommonService.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println(" Test de login");
        String username = "admin";
        String passwd = "admin";
       // IBanqueCommonService instance = new IBanqueCommonServiceImpl();
        User expResult = new User();
        expResult.setLogin("admin");
        expResult.setPassword("admin");
        expResult.setRole(RoleType.ADMIN);
        expResult.setStatus(0);
        expResult.setId(2);
        User result = service.login(username, passwd);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findCustomerByLogin method, of class IBanqueCommonService.
     */
    @Test
    public void testFindCustomerByLogin() throws Exception {
        System.out.println("findCustomerByLogin");
        String login = "";
        IBanqueCommonService instance = new IBanqueCommonServiceImpl();
        Customer expResult = null;
        Customer result = instance.findCustomerByLogin(login);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveOperation method, of class IBanqueCommonService.
     */
    @Test
    public void testSaveOperation() throws Exception {
        System.out.println("saveOperation");
        Operation op = null;
        IBanqueCommonService instance = new IBanqueCommonServiceImpl();
        instance.saveOperation(op);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findOperationByCriteria method, of class IBanqueCommonService.
     */
    @Test
    public void testFindOperationByCriteria() throws Exception {
        System.out.println("findOperationByCriteria");
        String accountNumber = "";
        String userName = "";
        OperationType opType = null;
        Date debut = null;
        Date fin = null;
        IBanqueCommonService instance = new IBanqueCommonServiceImpl();
        List<AccountOperation> expResult = null;
        List<AccountOperation> result = instance.findOperationByCriteria(accountNumber, userName, opType, debut, fin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IBanqueCommonServiceImpl implements IBanqueCommonService {

        public User login(String username, String passwd) throws ServiceException {
            return null;
        }

        public Customer findCustomerByLogin(String login) throws ServiceException {
            return null;
        }

        public void saveOperation(Operation op) throws ServiceException {
        }

        public List<AccountOperation> findOperationByCriteria(String accountNumber, String userName, OperationType opType, Date debut, Date fin) throws ServiceException {
            return null;
        }
    }
    
}
