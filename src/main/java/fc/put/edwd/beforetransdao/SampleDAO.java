package fc.put.edwd.beforetransdao;

import fc.put.edwd.dao.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by marcin on 26.10.16.
 */
public class SampleDAO extends BaseDAO {
    private static SampleDAO instance;

    private static final String INSERT = "INSERT INTO SAMPLE (USER_ID, SONG_ID, DATE) VALUES (?, ?, ?)";


    private SampleDAO(){}

    public static SampleDAO getInstance(){
        if (instance != null)
            return instance;
        synchronized (UserDAO.class){
            if (instance == null){
                instance = new SampleDAO();
            }
        }
        return instance;
    }

    public void insert(List<SampleMsg> msgList){
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(INSERT);
            int idx = 1;
            for (SampleMsg msg : msgList){
                idx = 1;
                stmt.setString(idx++, msg.getUserId());
                stmt.setInt(idx++, msg.getSongId());
                stmt.setLong(idx++, msg.getDate());
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
}
