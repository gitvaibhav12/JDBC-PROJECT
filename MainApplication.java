package JDBC;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Insert Data");
            System.out.println("2. Fetch Data");
            System.out.println("3. Update Data");
            System.out.println("4. Delete Data");
            System.out.println("5. Exit");

            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    insertData();
                    break;
                case 2:
                    fetchData();
                    break;
                case 3:
                    updateData();
                    break;
                case 4:
                    deleteData();
                    break;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void insertData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/August_Batch", "root", "vedmysql@123");

            String sql = "INSERT INTO JDBC (id, Name, Email, phoneNumber, Password) VALUES (?, ?, ?, ?, ?)";
      
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter your id");
            int id = scanner.nextInt();

            System.out.println("Enter your Name");
            String name = scanner.next();

            scanner.nextLine();
            System.out.println("Enter your Email id");
            String email = scanner.nextLine();

            System.out.println("Enter your ph no");
            String number = scanner.nextLine();

            System.out.println("Enter your password");
            String password = scanner.next();

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, number);
            preparedStatement.setString(5, password);

            int status = preparedStatement.executeUpdate();

            if (status > 0) {
                System.out.println("Your data has been inserted successfully");
            } else {
                System.out.println("Your data has not been inserted");
            }

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void fetchData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/August_Batch", "root", "vedmysql@123");

            String sql = "SELECT * FROM JDBC WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter the id to fetch data:");
            int idToFetch = scanner.nextInt();

            preparedStatement.setInt(1, idToFetch);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("phoneNumber");
                String password = rs.getString("Password");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("Phone Number: " + phoneNumber);
                System.out.println("Password: " + password);
            } else {
                System.out.println("No data found for the given ID");
            }

            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void updateData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/August_Batch", "root", "vedmysql@123");

            String sql = "UPDATE JDBC SET Name=?, Email=?, phoneNumber=?, Password=? WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter your Name");
            String Name = scanner.next();

            scanner.nextLine();
            System.out.println("Enter your Email id");
            String Email = scanner.nextLine();

            System.out.println("Enter your ph no");
            String phoneNumber = scanner.nextLine();

            System.out.println("Enter your password");
            String Password = scanner.next();

            System.out.println("Enter your id");
            int id = scanner.nextInt();


            preparedStatement.setString(1, Name);
            preparedStatement.setString(2, Email);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, Password);
            preparedStatement.setInt(5, id);

            int status = preparedStatement.executeUpdate();

            if (status > 0) {
                System.out.println("Your data has been successfully updated");
            } else {
                System.out.println("Your data has not been updated");
            }

            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void deleteData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/August_Batch", "root", "vedmysql@123");

            String sql = "DELETE FROM JDBC WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            Scanner sc = new Scanner(System.in);

            System.out.println("Enter your id which you want to delete:");
            int id = sc.nextInt();

            preparedStatement.setInt(1, id);

            int status = preparedStatement.executeUpdate();

            if (status > 0) {
                System.out.println("Your data has been deleted.");
            } else {
                System.out.println("Your data has not been deleted.");
            }

            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
