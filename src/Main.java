import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Admin> admins = new ArrayList<>();
        admins.add(new Admin("admin", "1100"));
        admins.add(new Admin("admin2", "1200"));

        System.out.println("Welcome to our Bank System!");
        System.out.println("----------------------------------------------------------------------------------------------");
        accounts.add(new Account("Ahmed", "1234", "1234"));
        accounts.add(new Account("Mohamed", "1244", "4321"));
        accounts.add(new Account("Ali", "1254", "5678"));
        customers.add(new Customer("Ahmed", "1234", "1234", "1234", 30, "Ahmed@12424", 2016));
         customers.add(new Customer("Mohamed", "1234", "4321", "4321", 45, "Mohamed.123@gmail.com", 2022));
        customers.add(new Customer("Ali", "1234", "5678", "1254", 0, null, 0));
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        while (true) {
            try {
                System.out.println("\nMain Menu:");
                System.out.println("1. Create Account");
                System.out.println("2. Login to Account");
                System.out.println("3. Exit");
                System.out.println("4. Admin Login");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                if (choice == 1) {
                    Account.createAccount(sc, accounts, customers, currentYear);
                } else if (choice == 2) {
                    // Login to an existing account
                    System.out.println("Enter your ID: ");
                    String id = sc.next();
                    System.out.println("Enter your password: ");
                    String password = sc.next();

                    if (Account.login(id, password, accounts)) {
                        while (true) {
                            try {
                                System.out.println("\nAccount Menu:");
                                System.out.println("1. Deposit");
                                System.out.println("2. Withdraw");
                                System.out.println("3. Check Balance");
                                System.out.println("4. Change Password");
                                System.out.println("5. Transfer");
                                System.out.println("6. Logout");
                                System.out.print("Enter your choice: ");
                                int option = sc.nextInt();

                                if (option == 1) {
                                    // Deposit
                                    System.out.println("Enter the amount to deposit: ");
                                    double amount = sc.nextDouble();
                                    for (Account acc : accounts) {
                                        if (acc.getId().equals(id)) {
                                            try {
                                                acc.deposit(amount);
                                            } catch (IllegalArgumentException e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        }
                                    }
                                } else if (option == 2) {
                                    // Withdraw
                                    System.out.println("Enter the amount to withdraw: ");
                                    double amount = sc.nextDouble();
                                    for (Account acc : accounts) {
                                        if (acc.getId().equals(id)) {
                                            try {
                                                acc.withdraw(amount);
                                            } catch (IllegalArgumentException e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        }
                                    }
                                } else if (option == 3) {
                                    for (Account acc : accounts) {
                                        if (acc.getId().equals(id)) {
                                            System.out.println("Current balance: " + acc.getBalance());
                                            break;
                                        }
                                    }
                                } else if (option == 4) {
                                    // Change password
                                    System.out.println("Enter your new password: ");
                                    String newPassword = sc.next();
                                    for (Account acc : accounts) {
                                        if (acc.getId().equals(id)) {
                                            try {
                                                acc.changePassword(password, newPassword);
                                            } catch (IllegalArgumentException e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        }
                                    }
                                } else if (option == 5) {
                                    // Transfer
                                    System.out.println("Enter the recipient's ID: ");
                                    String recipientId = sc.next();
                                    System.out.println("Enter the amount to transfer: ");
                                    double amount = sc.nextDouble();

                                    Account sourceAccount = null;
                                    Account recipientAccount = null;

                                    for (Account acc : accounts) {
                                        if (acc.getId().equals(id)) {
                                            sourceAccount = acc;
                                            break;
                                        }
                                    }

                                    for (Account acc : accounts) {
                                        if (acc.getId().equals(recipientId)) {
                                            recipientAccount = acc;
                                            break;
                                        }
                                    }

                                    if (sourceAccount != null && recipientAccount != null) {
                                        try {
                                            sourceAccount.transfer(sourceAccount, recipientAccount, amount);
                                        } catch (IllegalArgumentException e) {
                                            System.out.println(e.getMessage());
                                        }
                                    } else {
                                        System.out.println("Account not found for transfer.");
                                    }
                                } else if (option == 6) {
                                    // Logout
                                    System.out.println("Logged out successfully!");
                                    break;
                                } else {
                                    System.out.println("Invalid option. Please try again.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid number.");
                                sc.nextLine(); // Clear the invalid input
                            }
                        }
                    } else {
                        System.out.println("Invalid ID or password. Please try again.");
                    }

                } else if (choice == 3) {
                    // Exit the system
                    System.out.println("Thank you for using our Bank System!");
                    System.out.println("----------------------------------------------------------------------------------------------");
                    break;
                } else if (choice == 4) {
                    // Admin Login
                    System.out.println("Enter Admin ID: ");
                    String adminId = sc.next();
                    System.out.println("Enter Admin Password: ");
                    String adminPassword = sc.next();

                    Admin admin = new Admin();
                    if (admin.AreYouAdmin(adminId, adminPassword, admins)) {
                        System.out.println("Admin login successful!");

                        // Admin menu
                        while (true) {
                            try {
                                System.out.println("\nAdmin Menu:");
                                System.out.println("1. View All Customers");
                                System.out.println("2. block or Remove Customer");
                                System.out.println("3. Find Account by ID");
                                System.out.println("4. Log out");
                                System.out.print("Enter your choice: ");
                                int adminChoice = sc.nextInt();
                                Bank b = new Bank();

                                if (adminChoice == 1) {
                                    // View all customers
                                    b.ViewAllCustomers(customers);
                                    System.out.println("----------------------------------------------------------------------------------------------");
                                } else if (adminChoice == 2) {
                                    // Remove customer
                                    System.out.println("Enter the customer ID to remove: ");
                                    String customerId = sc.next();
                                    b.removeCustomer(customerId, customers, accounts);

                                } else if (adminChoice == 3) {
                                    // Find account by ID
                                    System.out.println("Enter the account ID to find: ");
                                    String accountId = sc.next();
                                    Account foundAccount = b.findAccountById(accountId, accounts);

                                    if (foundAccount != null) {
                                        System.out.println("Account found: " + foundAccount.getName()+" thier id  "+foundAccount.getId());
                                    } else {
                                        System.out.println("Account not found.");
                                    }

                                } else if (adminChoice == 4) {
                                    // Admin logout
                                    System.out.println("Logged out successfully!");
                                    break;

                                } else {
                                    System.out.println("Invalid choice. Please try again.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid number.");
                                sc.nextLine(); // Clear the invalid input
                            }
                        }
                    } else {
                        System.out.println("Admin login failed. Please try again.");
                    }
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine(); // Clear the invalid input
            }
        }
    
        sc.close();
    }
}


