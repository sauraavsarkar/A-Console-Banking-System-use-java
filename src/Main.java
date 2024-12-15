import Controller.users.login.LoginUser;
import Controller.users.create.RegisterUser;
import Controller.admin.create.CreateAdmin;
import Controller.admin.login.LoginAdmin;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to My Bank System");
        System.out.println("1. Register User");
        System.out.println("2. Login User");
        System.out.println("100. Create Admin");
        System.out.println("101. Login Admin");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        if (choice == 1) {
            RegisterUser.main(args);
        } else if (choice == 2) {
            LoginUser.main(args);
        } else if (choice == 100) {
            CreateAdmin.main(args);
        } else if (choice == 101) {
            LoginAdmin.main(args);
        } else {
            System.out.println("Invalid choice!");
        }
    }
}
