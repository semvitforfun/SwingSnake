package snake.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SQLiteConnection {

    private SQLiteConnection() {
    }
    private static SQLiteConnection instance;

    public static SQLiteConnection getInstance() {
        if (instance == null) {
            instance = new SQLiteConnection();
        }

        return instance;
    }
    
    private Connection con;
    private String path = "db/snake.db";

    public Connection getConnection() {
        try {

            if (con == null) {

                // динамическая регистрация драйвера SQLite
                Class.forName("org.sqlite.JDBC").newInstance();

                // создание подключение к базе данных по пути, указанному в урле
                String url = "jdbc:sqlite:" + path;

                con = DriverManager.getConnection(url);
            }

            return con;

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void closeConnection() {
        try {
            con.close();
            con = null;
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
    }

    
}
