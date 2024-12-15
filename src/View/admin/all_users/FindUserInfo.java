package View.admin.all_users;

import Model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FindUserInfo {
    public static void main(String[] args, Scanner scanner) {
        System.out.println("Search User");
        System.out.println("1. By UserID");
        System.out.println("2. By Name");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        if (choice == 1) {
            System.out.print("Enter UserID: ");
            String userId = scanner.nextLine();
            searchByUserID(userId);
        } else if (choice == 2) {
            System.out.print("Enter Name: ");
            String userName = scanner.nextLine();
            searchByName(userName);
        } else {
            System.out.println("Invalid choice!");
        }
    }

    public static void searchByUserID(String userId) {
        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            String sql = "SELECT * FROM user_info WHERE user_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, userId);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String userName = resultSet.getString("user_name");

                    System.out.println("ID: " + id + ", Name: " + userName + ", UserID: " + userId);
                } else {
                    System.out.println("No user found with UserID: " + userId);
                }

                connection.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void searchByName(String userName) {
        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            String sql = "SELECT * FROM user_info WHERE user_name LIKE ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, "%" + userName + "%");

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("user_name");
                    String userId = resultSet.getString("user_id");

                    System.out.println("ID: " + id + ", Name: " + name + ", UserID: " + userId);
                }

                connection.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
