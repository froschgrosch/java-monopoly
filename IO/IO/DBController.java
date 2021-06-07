package IO;

import java.sql.*;

class DBController {

    DBController dbcontroller = new DBController();
    Connection connection;
    String DB_PATH = System.getProperty("user.home") + "/" + "testdb.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Fehler beim Laden des JDBC-Treibers");
            e.printStackTrace();
        }
    }

    public DBController(){
    }


    private void initDBConnection() {
        try {
            if (connection != null)
                return;
            System.out.println("Creating Connection to Database...");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
            if (!connection.isClosed())
                System.out.println("...Connection established");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
                        if (!connection.isClosed() && connection != null) {
                            connection.close();
                            if (connection.isClosed())
                                System.out.println("Connection to Database closed");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
    }

    public void executeUpdate(String s){
        try {
            connection.createStatement().executeUpdate(s+";");
        } catch (Exception e){
            System.err.println("Couldn't send DB-Update");
            e.printStackTrace();
        }
    }

    private ResultSet query(String q) {
        ResultSet rs = null;
        try {
            rs = connection.createStatement().executeQuery(q+";");
        } catch (SQLException e) {
            System.err.println("Couldn't handle DB-Query");
            e.printStackTrace();
        }
        return rs;
    }
}