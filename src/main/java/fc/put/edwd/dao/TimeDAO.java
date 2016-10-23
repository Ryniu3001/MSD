package fc.put.edwd.dao;

import fc.put.edwd.dao.message.TimeMsg;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by marcin on 22.10.16.
 */
public class TimeDAO extends BaseDAO{
    private static TimeDAO instance;

    private static final String INSERT = "INSERT INTO TIME (HOUR, MINUTE) VALUES (?, ?)";
    private static final String GET = "SELECT * FROM TIME";

    private TimeDAO(){}

    public static TimeDAO getInstance(){
        if (instance != null)
            return instance;
        synchronized (UserDAO.class){
            if (instance == null){
                instance = new TimeDAO();
            }
        }
        return instance;
    }

    public void insert(List<TimeMsg> msgList){
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(INSERT);
            int idx = 1;
            for (TimeMsg msg : msgList){
                idx = 1;
                stmt.setInt(idx++, msg.getHour());
                stmt.setInt(idx, msg.getMinute());
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
    public Map<TimeMsg, Integer> get(){
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        ResultSet rs = null;
        Map<TimeMsg, Integer> map = new HashMap<>();
        try {
            stmt = conn.prepareStatement(GET);
            rs = stmt.executeQuery();
            while (rs.next()){
                Integer id = rs.getInt("ID");
                Integer hour = rs.getInt("HOUR");
                Integer minute = rs.getInt("MINUTE");
                map.put(new TimeMsg(id, hour, minute), id);
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
