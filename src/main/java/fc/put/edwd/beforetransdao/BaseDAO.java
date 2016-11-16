package fc.put.edwd.beforetransdao;


import fc.put.edwd.dao.UserDAO;
import fc.put.edwd.dao.message.SongMsg;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BaseDAO extends fc.put.edwd.dao.BaseDAO {
    private static BaseDAO instance;
    protected BaseDAO(){}

    public static BaseDAO getInstance(){
        if (instance != null)
            return instance;
        synchronized (UserDAO.class){
            if (instance == null){
                instance = new BaseDAO();
            }
        }
        return instance;
    }


    public void createIndexes(){
        Statement stmt = null;
        Connection conn = getConnection();
        Map<String, SongMsg> map = new HashMap<>();
        try {
            stmt = conn.createStatement();
/*            stmt.execute(SongDAO.RANKING_JOIN_INDEX);       //do rankingu piosenek
            stmt.execute(ListenDAO.RANKING_INDEX);          //
            stmt.execute(DateDAO.UNIQUE_DATE_INDEX);
            stmt.execute(ListenDAO.LISTEN_USER_IDX);
            stmt.execute(ListenDAO.LISTEN_ARTIST_IDX);
            stmt.execute(SongDAO.SONG_ARTIS_IDX);
            stmt.execute(ListenDAO.LISTEN_DATE_IDX);*/
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeStmt(stmt);
            closeConn(conn);
        }
    }

    protected static Connection getConnection(){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test2.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return c;
    }
}
