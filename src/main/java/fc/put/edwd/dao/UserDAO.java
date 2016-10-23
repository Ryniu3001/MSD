package fc.put.edwd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by marcin on 22.10.16.
 */
public class UserDAO extends BaseDAO {
    private static UserDAO instance;

    private static final String INSERT = "INSERT INTO USER (USER_F_ID) VALUES (?)";
    private static final String GET = "SELECT * FROM USER";


    private UserDAO(){}

    public static UserDAO getInstance(){
        if (instance != null)
            return instance;
        synchronized (UserDAO.class){
            if (instance == null){
                instance = new UserDAO();
            }
        }
        return instance;
    }

    public void insert(Set<String> users){
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            int idx = 1;
            stmt = conn.prepareStatement(INSERT);
            for (String msg : users){
                stmt.setString(idx, msg);
                stmt.addBatch();
            }
            conn.setAutoCommit(false);
            stmt.executeBatch();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeStmt(stmt);
            closeConn(conn);
        }
    }

    public Map<String, Integer> get(){
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        ResultSet rs = null;
        Map<String, Integer> map = new HashMap<>();
        try {
            stmt = conn.prepareStatement(GET);
            rs = stmt.executeQuery();
            while (rs.next()){
                Integer id = rs.getInt("ID");
                String fId = rs.getString("USER_F_ID");
                map.put(fId, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeRs(rs);
            closeStmt(stmt);
            closeConn(conn);
        }
        return map;
    }
}
