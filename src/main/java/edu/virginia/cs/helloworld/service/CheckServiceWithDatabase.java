package edu.virginia.cs.helloworld.service;
import org.springframework.stereotype.Service;

import java.sql.*;
@Service
public class CheckServiceWithDatabase {
    public boolean checkUserName(String user, String pass) {
        if (user == null || pass == null) {
            return false;
        }
        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test",
                    "Matthew", "@dm!n12EAS");

            // mydb is database
            // mydbuser is name of database
            // mydbuser is password of database

            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "select * from users");
            String username;
            String password;
            while (resultSet.next()) {
                username = resultSet.getString("username").trim();
                password = resultSet.getString("password").trim();
                if (username.equals(user)) {
                    if (password.equals(pass)) {
                        return true;
                    } else {
                        return false;
                    }

                }
            }

            resultSet.close();
            statement.close();
            connection.close();

        }
        catch (Exception exception) {
            System.out.println(exception);
        }
        return false;
    }
}
