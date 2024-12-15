package Controller.admin.login;

import Model.DatabaseConnection;
import View.admin.all_users.ViewAllUsersForAdmin;
import View.admin.all_users.FindUserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginAdmin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Admin Login");
        System.out.print("Enter Admin ID: ");
        String adminId = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String adminPass = scanner.nextLine();

        loginAdmin(adminId, adminPass, scanner);
    }

    public static void loginAdmin(String adminId, String adminPass, Scanner scanner) {
        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            String sql = "SELECT * FROM admin_info WHERE admin_id = ? AND admin_pass = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, adminId);
                preparedStatement.setString(2, adminPass);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("Login successful! Welcome, " + resultSet.getString("admin_name"));

                    System.out.println("Choose an option:");
                    System.out.println("1. View All Users");
                    System.out.println("2. Search User by UserID/Name");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over

                    if (choice == 1) {
                        ViewAllUsersForAdmin.viewAllUsers(scanner); // Pass scanner
                    } else if (choice == 2) {
                        FindUserInfo.main(new String[]{}, scanner); // Pass scanner
                    } else {
                        System.out.println("Invalid choice!");
                    }
                } else {
                    System.out.println("Invalid Admin ID or Password!");
                }

                connection.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
