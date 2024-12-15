package Controller.admin.create;

import Model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CreateAdmin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Admin Creation");
        System.out.print("Enter Admin Name: ");
        String adminName = scanner.nextLine();

        System.out.print("Enter Admin ID: ");
        String adminId = scanner.nextLine();

        System.out.print("Enter Admin Password: ");
        String adminPass = scanner.nextLine();

        createAdmin(adminName, adminId, adminPass);
    }

    public static void createAdmin(String adminName, String adminId, String adminPass) {
        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            String sql = "INSERT INTO admin_info (admin_name, admin_id, admin_pass) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, adminName);
                preparedStatement.setString(2, adminId);
                preparedStatement.setString(3, adminPass);

                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Admin created successfully!");
                }

                connection.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
