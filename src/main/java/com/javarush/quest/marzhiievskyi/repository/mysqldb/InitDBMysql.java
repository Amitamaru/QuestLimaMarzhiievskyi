package com.javarush.quest.marzhiievskyi.repository.mysqldb;

import java.sql.*;

public class InitDBMysql {

    static {
        try {
            Class.forName("java.sql.DriverManager");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("""
                    SELECT * FROM t_user;
                    """);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                System.out.println(resultSet.getRow() + ". " + id + "\t" + login + "\t" + password);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/quest_db",
                "root",
                "mysql");
    }
}
