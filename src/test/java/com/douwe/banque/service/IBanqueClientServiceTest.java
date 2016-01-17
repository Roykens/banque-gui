package com.douwe.banque.service;

import com.douwe.banque.model.Account;
import com.douwe.banque.model.projection.AccountOperation;
import com.douwe.banque.service.impl.BanqueClientServiceImpl;
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
public class IBanqueClientServiceTest {
    
    private IBanqueClientService clientService;
    
    public IBanqueClientServiceTest() {
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
        clientService = new BanqueClientServiceImpl();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findAccountByCustomerId method, of class IBanqueClientService.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindAccountByCustomerId() throws Exception {
        System.out.println("findAccountByCustomerId");
        Integer id = 2;
        List<Account> expResult = null;
        List<Account> result = clientService.findAccountByCustomerId(id);
        assertEquals(null, null);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of transfer method, of class IBanqueClientService.
     * @throws java.lang.Exception
     */
    @Test
    public void testTransfer() throws Exception {
        System.out.println("transfer");
        String depart = "";
        String destination = "";
        double montant = 0.0;
        int userId = 0;
       // clientService.transfer(depart, destination, montant, userId);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of findOperationFromCustomerAccounts method, of class IBanqueClientService.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindOperationFromCustomerAccounts() throws Exception {
        System.out.println("findOperationFromCustomerAccounts");
        int customerId = 0;
        List<AccountOperation> expResult = null;
      //  List<AccountOperation> result = clientService.findOperationFromCustomerAccounts(customerId);
      //  assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
