import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

    public class Account {

    private String name;
    private String id;
    private double balance;
    private String password;
 
    public Account(){}
    // Constructor
    public Account(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.balance = 0;
        

     }
  
    public void setBalance(double balance){
        this.balance = balance;
    }
    public String getName() {
        return name;
    }

    public  String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public static void createAccount(Scanner sc, ArrayList<Account> accounts, ArrayList<Customer> customers,int year) {
        System.out.println("Enter your name: ");
        String name = sc.next();
        System.out.println("Enter your address: ");
        String address = sc.next();
        System.out.println("Enter your phone: ");
        String phone = sc.next();
        System.out.println("Enter your age: ");
        int age = sc.nextInt();
        System.out.println("Enter your email: ");
        String email = sc.next();
        System.out.println("Enter your password: ");
        String password = sc.next();
        // Generate a random ID by appending a random number between 1 and 999 to the name
        String id = name.concat(String.valueOf(new Random().nextInt(999) + 1));

        Account account = new Account(name, id, password);
        Customer customer = new Customer(name, address, phone, id, age, email, year);
        accounts.add(account);
        customers.add(customer);

        System.out.println("Account created successfully!");
        System.out.println("Welcome to our banking system! " + name);
        System.out.print("(");
        System.out.println("Your ID is: " + id);
        System.out.print("Your password is: " + password);
        System.out.println(")");
    }
    // Login Method
    public static boolean login(String id, String password, ArrayList<Account> accounts) {
        // Check if the account exists
        if (findAccount(id, accounts)) {
            for (Account account : accounts) {
                if (account.getId().equals(id)) {
                    // Validate password
                    if (account.password.equals(password)) {
                        System.out.println("Welcome! "+account.getName());
                        return true;
                    } else {
                        System.out.println("Wrong password.");
                        return false;
                    }
                }
            }
        }
        System.out.println("Account not found.");
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposit successful! Your balance is now: " + balance);
        } else {
            System.out.println("Please enter a valid amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            System.out.println("Withdrawal successful! Your balance is now: " + balance);
        } else {
            System.out.println("Please enter a valid amount.");
        }
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (oldPassword.equals(password)) {
            this.password = newPassword;
            System.out.println("Password changed successfully!");
            System.out.println("Your new password is: " + newPassword);
        } else {
            System.out.println("Wrong password.");
        }
    }
    public static void transfer(Account sourceAccount, Account destinationAccount, double amount) {
        if (amount > 0 && sourceAccount.getBalance() >= amount) {
            // Deduct from source
            sourceAccount.withdraw(amount);
            // Add to destination
            destinationAccount.deposit(amount);
            System.out.println("Transfer successful! " + amount + " has been transferred from " + 
                                sourceAccount.getName() + " to " + destinationAccount.getName());
            System.out.println("Your balance now is: " + sourceAccount.getBalance());
        } else {
            System.out.println("Transfer failed: Insufficient funds or invalid amount.");
        }
    }
    // Helper Method to Find Account
    private static boolean findAccount(String id, ArrayList<Account> accounts) {
        for (Account account :accounts) {
            if (account.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account {" +
                "Name='" + name + '\'' +
                ", ID='" + id + '\'' +
                ", Balance=" + balance +
                '}';
    }

    void setPassword(String newPassword) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
