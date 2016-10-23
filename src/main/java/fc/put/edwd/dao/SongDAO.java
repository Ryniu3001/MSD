package fc.put.edwd.dao;

import fc.put.edwd.dao.message.SongMsg;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by marcin on 22.10.16.
 */
public class SongDAO extends BaseDAO{

    private static SongDAO instance;

    private static final String INSERT = "INSERT INTO SONG (SONG_ID, ARTIST_ID, TITLE) VALUES (?, ?, ?)";
    private static final String GET = "SELECT * FROM SONG";

    public static final String RANKING_JOIN_INDEX = "CREATE UNIQUE INDEX SONG_SONG_ID_IDX ON SONG (SONG_ID)";
    public static final String SONG_ARTIS_IDX = "CREATE INDEX SONG_ARTIST_IDX ON SONG (ARTIST_ID)";


    private SongDAO(){}

    public static SongDAO getInstance(){
        if (instance != null)
            return instance;
        synchronized (UserDAO.class){
            if (instance == null){
                instance = new SongDAO();
            }
        }
        return instance;
    }

    public void insert(Set<SongMsg> msgList){
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(INSERT);
            int idx = 1;
            for (SongMsg msg : msgList){
                idx = 1;
                stmt.setString(idx++, msg.getSongId());
                stmt.setInt(idx++, msg.getArtistId());
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

    public Map<String, SongMsg> get(){
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        ResultSet rs = null;
        Map<String, SongMsg> map = new HashMap<>();
        try {
            stmt = conn.prepareStatement(GET);
            rs = stmt.executeQuery();
            while (rs.next()){
                Integer id = rs.getInt("ID");
                String songId = rs.getString("SONG_ID");
                Integer artistId = rs.getInt("ARTIST_ID");
                String title = rs.getString("TITLE");
                map.put(songId, new SongMsg(id, songId, artistId, title));
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
