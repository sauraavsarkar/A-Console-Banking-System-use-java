package Controller.users.login;

import Model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginUser {

    public static void loginUser(String userId, String userPass) {
        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            String sql = "SELECT * FROM user_info WHERE user_id = ? AND user_pass = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, userPass);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("Login successful! Welcome, " + resultSet.getString("user_name"));
                } else {
                    System.out.println("Invalid User ID or Password!");
                }

                connection.close();
            } catch (SQLException e) {
                System.out.println("Error:" + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("User Login");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Password: ");
        String userPass = scanner.nextLine();

        loginUser(userId, userPass);
    }
}
