package fc.put.edwd.badsolutiondao;

import fc.put.edwd.dao.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by marcin on 26.10.16.
 */
public class TrackDAO extends BaseDAO{
    private static TrackDAO instance;

    private static final String INSERT = "INSERT INTO TRACK (SONG_ID, ARTIST, TITLE) VALUES (?, ?, ?)";
    private static final String GET = "SELECT * FROM TRACK";

    private TrackDAO(){}

    public static TrackDAO getInstance(){
        if (instance != null)
            return instance;
        synchronized (UserDAO.class){
            if (instance == null){
                instance = new TrackDAO();
            }
        }
        return instance;
    }

    public void insert(Set<TrackMsg> msgList){
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(INSERT);
            int idx = 1;
            for (TrackMsg msg : msgList){
                idx = 1;
                stmt.setString(idx++, msg.getSongId());
                stmt.setString(idx++, msg.getArtist());
                stmt.setString(idx, msg.getTitle());
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

    public Map<String, TrackMsg> get(){
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        ResultSet rs = null;
        Map<String, TrackMsg> map = new HashMap<>();
        try {
            stmt = conn.prepareStatement(GET);
            rs = stmt.executeQuery();
            while (rs.next()){
                Integer id = rs.getInt("ID");
                String songId = rs.getString("SONG_ID");
                String artist = rs.getString("ARTIST");
                String title = rs.getString("TITLE");
                map.put(songId, new TrackMsg(id, songId, artist, title));
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
