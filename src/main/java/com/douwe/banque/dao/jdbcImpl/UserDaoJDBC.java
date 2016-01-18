package com.douwe.banque.dao.jdbcImpl;

import com.douwe.banque.dao.DataAccessException;
import com.douwe.banque.dao.IUserDao;
import com.douwe.banque.data.RoleType;
import com.douwe.banque.model.Account;
import com.douwe.banque.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public class UserDaoJDBC implements IUserDao {

    @Override
    public User save(User user) throws DataAccessException {
        try {
            Connection conn = JDBCConnectionFactory.getConnection();
            PreparedStatement psmt = conn.prepareStatement("insert into users(username, passwd, role, status) values(?,?,?,?)");
            psmt.setString(1, user.getLogin());
            psmt.setString(2, user.getPassword());
            psmt.setInt(3, user.getRole().ordinal());
            psmt.setInt(4, user.getStatus());
            psmt.executeUpdate();
            ResultSet rs = psmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
            rs.close();
            psmt.close();
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }
    }

    @Override
    public void delete(User user) throws DataAccessException {
        try {
            Connection conn = JDBCConnectionFactory.getConnection();
            PreparedStatement psmt = conn.prepareStatement("delete from users where id = ?");
            psmt.setLong(1, user.getId());
            psmt.executeUpdate();
            psmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }
    }

    @Override
    public User update(User user) throws DataAccessException {
        try {
            Connection conn = JDBCConnectionFactory.getConnection();
            PreparedStatement psmt = conn.prepareStatement("update users set username = ?, passwd = ?, role = ?, status = ? where id = ?");
            psmt.setString(1, user.getLogin());
            psmt.setString(2, user.getPassword());
            psmt.setInt(3, user.getRole().ordinal());
            psmt.setInt(4, user.getStatus());
            psmt.setLong(5, user.getId());
            psmt.executeUpdate();
            psmt.close();
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }

    }

    @Override
    public User findById(Integer id) throws DataAccessException {
        try {
            Connection conn = JDBCConnectionFactory.getConnection();
            PreparedStatement psmt = conn.prepareStatement("select * from users where id = ?");
            psmt.setInt(1, id);
            return executeQueryResult(psmt);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }
    }

    @Override
    public List<User> findAll() throws DataAccessException {
        try {
            Connection conn = JDBCConnectionFactory.getConnection();
            PreparedStatement psmt = conn.prepareStatement("select * from users");
            return executeQuery(psmt);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }
    }

    @Override
    public User findByLogin(String login) throws DataAccessException {
        try {
            Connection conn = JDBCConnectionFactory.getConnection();
            PreparedStatement psmt = conn.prepareStatement("select * from users where username like ?");
            psmt.setString(1, login);                  
            return executeQueryResult(psmt);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }
    }

    @Override
    public List<User> findByStatus(int status) throws DataAccessException {
        try {
            Connection conn = JDBCConnectionFactory.getConnection();
            PreparedStatement psmt = conn.prepareStatement("select * from users where status = ?");
            psmt.setInt(1, status);
            return executeQuery(psmt);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }
    }

    @Override
    public User findUserByAccount(Account account) throws DataAccessException {
        try {
            Connection conn = JDBCConnectionFactory.getConnection();
            PreparedStatement psmt = conn.prepareStatement("select u.id , u.username, u.passwd, u.role, u.status from users u where u.id=(select customer.user_id from customer  where customer.id= (select account.customer_id from account where account.id= ?))");
            psmt.setInt(1, account.getId());
            return executeQueryResult(psmt);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }
    }

    private User executeQueryResult(PreparedStatement psmt) throws DataAccessException {
        User user = null;
        try {
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                user = SaveUser(rs);
            }
            rs.close();
            psmt.close();
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }
    }

    private User SaveUser(ResultSet rs) throws DataAccessException {
        try {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("username"));
            user.setPassword(rs.getString("passwd"));
            user.setRole(RoleType.values()[rs.getInt("role")]);
            user.setStatus(rs.getInt("status"));
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }
    }

    private List<User> executeQuery(PreparedStatement psmt) throws DataAccessException {
        List<User> resultat = new ArrayList<>();
        try {
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                User user = SaveUser(rs);
                resultat.add(user);
            }
            rs.close();
            psmt.close();
            return resultat;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }

    }

}
