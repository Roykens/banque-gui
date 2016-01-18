package com.douwe.banque.service;

import com.douwe.banque.dao.IUserDao;
import com.douwe.banque.data.RoleType;
import com.douwe.banque.model.User;
import com.douwe.banque.service.impl.BanqueServiceCommonImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.easymock.EasyMock;
import static org.easymock.EasyMock.createStrictMock;
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

    private BanqueServiceCommonImpl service;

    private IUserDao mockDao;

    public IBanqueCommonServiceTest() {
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
        mockDao = createStrictMock(IUserDao.class);
        service.setUserDao(mockDao);
    }

    @After
    public void tearDown() {
//        EasyMock.verify(service);
  //      service = null;
    }

    /**
     * Test of login method, of class IBanqueCommonService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println(" Test de login");
        String username = "admin";
        String passwd = "admin";
        User user = new User();
        
        user.setLogin("admin");
        user.setPassword("admin");
        user.setRole(RoleType.admin);
        user.setStatus(0);
        EasyMock.expect(mockDao.save(user)).andReturn(user);
   //     EasyMock.expect(service.login(username,passwd)).andReturn(user);
        EasyMock.replay(mockDao);


        User result = service.login(username,passwd);
        System.out.println(result);
        assertNotNull(result);
        assertEquals(user, result);
    }

}
