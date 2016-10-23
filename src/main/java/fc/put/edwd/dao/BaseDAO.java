package fc.put.edwd.dao;


import fc.put.edwd.dao.message.SongMsg;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BaseDAO {

    public static void createTables(){
        Connection conn = getConnection();
    }


    protected void createIndexes(){
        Statement stmt = null;
        Connection conn = getConnection();
        Map<String, SongMsg> map = new HashMap<>();
        try {
            stmt = conn.createStatement();
            stmt.execute(SongDAO.RANKING_JOIN_INDEX);       //do rankingu piosenek
            stmt.execute(ListenDAO.RANKING_INDEX);          //
            stmt.execute(DateDAO.UNIQUE_DATE_INDEX);
            stmt.execute(ListenDAO.LISTEN_USER_IDX);
            stmt.execute(ListenDAO.LISTEN_ARTIST_IDX);
            stmt.execute(SongDAO.SONG_ARTIS_IDX);
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
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return c;
    }

    protected void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void closeStmt(Statement stmt){
        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void closeRs(ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
