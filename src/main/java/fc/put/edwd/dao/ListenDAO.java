package fc.put.edwd.dao;

import fc.put.edwd.dao.message.ListenMsg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by marcin on 22.10.16.
 */
public class ListenDAO extends BaseDAO{

    private static ListenDAO instance;

    private static final String INSERT = "INSERT INTO LISTEN (SONG_ID, DATE_ID, TIME_ID, USER_ID, ARTIST_ID) VALUES (?, ?, ?, ?, ?)";
    protected static final String RANKING_INDEX = "CREATE INDEX LISTEN_SONG_ID_IDX ON LISTEN (SONG_ID DESC)";
    protected static final String LISTEN_USER_IDX = "CREATE INDEX LISTEN_USER_IDX ON LISTEN (USER_ID)";
    protected static final String LISTEN_ARTIST_IDX ="CREATE INDEX LISTEN_ARTIST_IDX ON LISTEN (ARTIST_ID)";


    private ListenDAO(){}

    public static ListenDAO getInstance(){
        if (instance != null)
            return instance;
        synchronized (UserDAO.class){
            if (instance == null){
                instance = new ListenDAO();
            }
        }
        return instance;
    }

    public void insert(List<ListenMsg> msgList){
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(INSERT);
            int idx = 1;
            for (ListenMsg msg : msgList){
                idx = 1;
                stmt.setInt(idx++, msg.getSongId());
                stmt.setInt(idx++, msg.getDateId());
                stmt.setInt(idx++, msg.getTimeId());
                stmt.setInt(idx++, msg.getUserId());
                stmt.setInt(idx, msg.getArtistId());
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
