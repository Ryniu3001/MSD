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
public class ArtistDAO extends BaseDAO {
    private static ArtistDAO instance;

    private static final String INSERT = "INSERT INTO ARTIST (NAME) VALUES (?)";
    private static final String GET = "SELECT * FROM ARTIST";


    private ArtistDAO(){}

    public static ArtistDAO getInstance(){
        if (instance != null)
            return instance;
        synchronized (UserDAO.class){
            if (instance == null){
                instance = new ArtistDAO();
            }
        }
        return instance;
    }

    public void insert(Set<String> msgList){
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(INSERT);
            int idx = 1;
            for (String name : msgList){
                stmt.setString(idx, name);
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
        Map<String, Integer> artistMap = new HashMap<>();
        try {
            stmt = conn.prepareStatement(GET);
            rs = stmt.executeQuery();
            while (rs.next()){
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                artistMap.put(name, id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeRs(rs);
            closeStmt(stmt);
            closeConn(conn);
        }
        return artistMap;
    }
}
