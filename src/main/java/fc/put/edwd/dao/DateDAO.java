package fc.put.edwd.dao;

import fc.put.edwd.dao.message.DateMsg;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by marcin on 22.10.16.
 */
public class DateDAO extends BaseDAO{
    private static DateDAO instance;

    private static final String INSERT = "INSERT INTO DATE (YEAR, MONTH, DAY) VALUES (?, ?, ?)";
    private static final String GET = "SELECT * FROM DATE";
    protected static final String UNIQUE_DATE_INDEX = "CREATE UNIQUE INDEX UNIQUE_DATE_IDX ON DATE (YEAR, MONTH, DAY)";

    private DateDAO(){}

    public static DateDAO getInstance(){
        if (instance != null)
            return instance;
        synchronized (UserDAO.class){
            if (instance == null){
                instance = new DateDAO();
            }
        }
        return instance;
    }

    public void insert(Set<DateMsg> msgList){
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(INSERT);
            for (DateMsg msg : msgList){
                int idx = 1;
                stmt.setInt(idx++, msg.getYear());
                stmt.setInt(idx++, msg.getMonth());
                stmt.setInt(idx, msg.getDay());
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

    public Map<DateMsg, Integer> get(){
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        ResultSet rs = null;
        Map<DateMsg, Integer> map = new HashMap<>();
        try {
            stmt = conn.prepareStatement(GET);
            rs = stmt.executeQuery();
            while (rs.next()){
                Integer id = rs.getInt("ID");
                Integer year = rs.getInt("YEAR");
                Integer month = rs.getInt("MONTH");
                Integer day = rs.getInt("DAY");
                map.put(new DateMsg(year, month, day), id);
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
