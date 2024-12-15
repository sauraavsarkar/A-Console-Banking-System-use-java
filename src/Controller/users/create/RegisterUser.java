package Controller.users.create;

import Model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class RegisterUser {

    public static void registerUser(String userName, String userId, String userPass) {
        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            String sql = "INSERT INTO user_info (user_name, user_id, user_pass) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, userId);
                preparedStatement.setString(3, userPass);

                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("User registered successfully!");
                }

                connection.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("User Registration");
        System.out.print("Enter User Name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Password: ");
        String userPass = scanner.nextLine();

        registerUser(userName, userId, userPass);
    }
}
