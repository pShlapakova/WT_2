package service;

import exception.DBException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBService {
    private static DBService instance = null;
    private Connection connection;

    private boolean isReady;

    private final String url = "jdbc:mysql://localhost/park?useTimezone=true&serverTimezone=UTC";
    private String user = "root";
    private String password = "1234";
    private final String filepathDB = "E:\\Университет\\ВТ\\lab_2\\src\\main\\resources\\database.sql";

    private DBService() {
    }

    public static DBService getInstance() {
        if (instance == null) {
            instance = new DBService();
        }
        return instance;
    }

    public Connection getConnection() throws DBException {
        if (isReady) {
            return connection;
        } else {
            try {
                //Class.forName("com.MySQL.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
                initDataBase();
                isReady = true;
                return connection;
            } catch (/*ClassNotFoundException |*/ SQLException e) {
                isReady = false;
                throw new DBException(e.getMessage());
            }
        }
    }

    private void initDataBase() throws DBException {

        try {
            Statement statement = connection.createStatement();

            FileReader reader = new FileReader(filepathDB);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String sqlCommand;
            StringBuilder buffer = new StringBuilder();
            while ((sqlCommand = bufferedReader.readLine()) != null) {
                buffer.append(sqlCommand);
                if (sqlCommand.contains(";")) {
                    statement.execute(buffer.toString());
                    buffer = new StringBuilder();
                }
            }
        } catch (SQLException | IOException e) {
            throw new DBException(e.getMessage());
        }
    }
}
