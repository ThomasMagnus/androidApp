package connection;

import android.util.Log;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionDb {
    public String connectionString, dbUser, dbPassword;
    private Connection connect;

    public ConnectionDb(String connectionString, String dbUser, String dbPassword) {
        this.connectionString = connectionString;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public void createConnection()  {
        try {
            this.connect = DriverManager.getConnection(this.connectionString, this.dbUser, this.dbPassword);
            Log.d("Соединение с базой", "Соединение открыто!");
        } catch (SQLException ex) {
            Log.d("Соединение с базой", "Ошибка соединения!");
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void closeConnection() {
        try {this.connect.close();}
        catch (SQLException ex) {
            Log.d("Соединение с базой", "Ошибка закрытия соединения");
            throw new RuntimeException(ex.getMessage());
        }
    }
}
