package com.douwe.banque.dao.jdbcimpl;

import com.douwe.banque.dao.DataAccessException;
import com.douwe.banque.dao.IOperationDao;
import com.douwe.banque.data.AccountType;
import com.douwe.banque.data.OperationType;
import com.douwe.banque.data.RoleType;
import com.douwe.banque.model.Account;
import com.douwe.banque.model.Customer;
import com.douwe.banque.model.Operation;
import com.douwe.banque.model.User;
import java.sql.Connection;
import java.sql.Date;
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
public class OperationDaoJDBC implements IOperationDao {

    @Override
    public Operation save(Operation operation) throws DataAccessException {
        try {
            Connection conn = JDBCConnectionFactory.getConnection();
            PreparedStatement psmt = conn.prepareStatement("insert into operations(dateOperation, description, account_id, user_id, operationType) values(?,?,?,?,?)");
            psmt.setDate(1, new java.sql.Date(operation.getDateOperation().getTime()));
            psmt.setString(2, operation.getDescription());
            if (operation.getAccount() != null) {
                psmt.setLong(3, operation.getAccount().getId());
            } else {
                psmt.setLong(3, 1L);
            }
            if (operation.getUser() != null) {
                psmt.setLong(4, operation.getUser().getId());
            } else {
                psmt.setLong(4, 1L);
            }
            psmt.setInt(5, operation.getType().ordinal());
            psmt.executeUpdate();
            ResultSet rs = psmt.getGeneratedKeys();
            while (rs.next()) {
                operation.setId(rs.getInt(1));
            }
            rs.close();
            psmt.close();
            return operation;
        } catch (SQLException ex) {
            Logger.getLogger(OperationDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }
    }

    @Override
    public void delete(Operation operation) throws DataAccessException {
        try {
            Connection conn = JDBCConnectionFactory.getConnection();
            PreparedStatement psmt = conn.prepareStatement("delete from operations where id = ?");
            psmt.setLong(1, operation.getId());
            psmt.executeUpdate();
            psmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(OperationDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }
    }

    @Override
    public Operation update(Operation operation) throws DataAccessException {
        try {
            Connection conn = JDBCConnectionFactory.getConnection();
            PreparedStatement psmt = conn.prepareStatement("update operations set dateOperation = ?, description = ?, account_id = ?, user_id = ?, operationType = ? where id = ?");
            psmt.setDate(1, new Date(operation.getDateOperation().getTime()));
            psmt.setString(2, operation.getDescription());
            psmt.setLong(3, operation.getAccount().getId());
            psmt.setLong(4, operation.getUser().getId());
            psmt.setInt(5, operation.getType().ordinal());
            psmt.setLong(6, operation.getId());
            psmt.executeUpdate();
            psmt.close();
            return operation;
        } catch (SQLException ex) {
            Logger.getLogger(OperationDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }
    }

    @Override
    public Operation findById(Integer id) throws DataAccessException {
        try {
            Connection conn = JDBCConnectionFactory.getConnection();
            PreparedStatement psmt = conn.prepareStatement("select o.*, u.id as uid, u.username, u.passwd, u.role, u.status, a.id as aid, a.accountNumber, a.balance, a.dateCreation, a.type, a.status as astatus from operations o, users u, account a where o.id = ? and o.user_id = u.id and o.account_id = a.id");
            psmt.setInt(1, id);
            return executeQuery(psmt);
        } catch (SQLException ex) {
            Logger.getLogger(OperationDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }
    }

    @Override
    public List<Operation> findAll() throws DataAccessException {
        try {

            Connection conn = JDBCConnectionFactory.getConnection();
            PreparedStatement psmt = conn.prepareStatement("select o.*, u.id as uid, u.username, u.passwd, u.role, u.status, a.id as aid, a.accountNumber, a.balance, a.dateCreation, a.type, a.status as astatus from operations o, users u, account a where o.user_id = u.id and o.account_id = a.id");
            return executeQueryResult(psmt);
        } catch (SQLException ex) {
            Logger.getLogger(OperationDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }
    }

    @Override
    public List<Operation> findForCustomer(Customer cust) throws DataAccessException {
        try {

            Connection conn = JDBCConnectionFactory.getConnection();
            PreparedStatement psmt = conn.prepareStatement("select o.*, u.id as uid, u.username, u.passwd, u.role, u.status, a.id as aid, a.accountNumber, a.balance, a.dateCreation, a.type, a.status as astatus from operations o, users u, account a where o.user_id = u.id and o.account_id = a.id and a.customer_id = ?");
            psmt.setInt(1, cust.getId());
            return executeQueryResult(psmt);
        } catch (SQLException ex) {
            Logger.getLogger(OperationDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }
    }

    private List<Operation> executeQueryResult(PreparedStatement psmt) throws DataAccessException {
        try {
            List<Operation> result = new ArrayList<>();
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Operation op = stockerOperation(rs);
                result.add(op);
            }
            rs.close();
            psmt.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(OperationDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }
    }

    private Operation executeQuery(PreparedStatement psmt) throws DataAccessException {
        try {
            Operation op = null;
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                op = stockerOperation(rs);
            }
            rs.close();
            psmt.close();
            return op;
        } catch (SQLException ex) {
            Logger.getLogger(OperationDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }
    }

    private Operation stockerOperation(ResultSet rs) throws DataAccessException {
        try {
            Operation op = new Operation();
            op.setId(rs.getInt("id"));
            op.setDateOperation(rs.getDate("dateOperation"));
            op.setDescription(rs.getString("description"));
            op.setType(OperationType.values()[rs.getInt("operationType")]);
            User user = new User();
            user.setId(rs.getInt("uid"));
            user.setLogin(rs.getString("username"));
            user.setPassword(rs.getString("passwd"));
            user.setRole(RoleType.values()[rs.getInt("role")]);
            user.setStatus(rs.getInt("status"));
            op.setUser(user);
            Account account = new Account();
            account.setAccountNumber(rs.getString("accountNumber"));
            account.setBalance(rs.getDouble("balance"));
            account.setDateDeCreation(rs.getDate("dateOperation"));
            account.setType(AccountType.values()[rs.getInt("type")]);
            account.setStatus(rs.getInt("astatus"));
            op.setAccount(account);
            return op;
        } catch (SQLException ex) {
            Logger.getLogger(OperationDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException(ex);
        }
    }
}
