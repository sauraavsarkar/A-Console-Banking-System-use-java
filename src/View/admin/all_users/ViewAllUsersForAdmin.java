package View.admin.all_users;

import Model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ViewAllUsersForAdmin {
    public static void viewAllUsers(Scanner scanner) {
        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            String sql = "SELECT * FROM user_info";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                System.out.println("All Users:");

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String userName = resultSet.getString("user_name");
                    String userId = resultSet.getString("user_id");

                    System.out.println("ID: " + id + ", Name: " + userName + ", UserID: " + userId);
                }

                connection.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
