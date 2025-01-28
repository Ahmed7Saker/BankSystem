import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;

public class App extends Application {
    private ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Admin> admins = new ArrayList<>();
    private Stage primaryStage;
    private Scene loginScene;
    private Scene adminScene;
    private Scene customerScene;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initializeData();
        createLoginScene();
        
        primaryStage.setTitle("Modern Bank Management System");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void initializeData() {
        // Initialize sample data
        admins.add(new Admin("admin", "1100"));
        admins.add(new Admin("admin2", "1200"));
        
        accounts.add(new Account("Ahmed", "1234", "1234"));
        accounts.add(new Account("Mohamed", "1244", "4321"));
        accounts.add(new Account("Ali", "1254", "5678"));
        
        customers.add(new Customer("Ahmed", "Cairo", "1234", "1234", 30, "Ahmed@12424", 2016));
        customers.add(new Customer("Mohamed", "Alex", "4321", "1244", 45, "Mohamed.123@gmail.com", 2022));
        customers.add(new Customer("Ali", "Giza", "5678", "1254", 25, "ali@mail.com", 2023));
    }

    private void createLoginScene() {
        VBox loginLayout = new VBox(20);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setPadding(new Insets(40));
        loginLayout.setStyle("-fx-background-color: #f4f4f4;");

        Label titleLabel = new Label("Bank Management System");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.DARKBLUE);

        Button customerLoginBtn = createStyledButton("Customer Login");
        Button adminLoginBtn = createStyledButton("Admin Login");
        Button createAccountBtn = createStyledButton("Create New Account");

        customerLoginBtn.setOnAction(e -> showCustomerLoginDialog());
        adminLoginBtn.setOnAction(e -> showAdminLoginDialog());
        createAccountBtn.setOnAction(e -> showCreateAccountDialog());

        loginLayout.getChildren().addAll(titleLabel, customerLoginBtn, adminLoginBtn, createAccountBtn);
        loginScene = new Scene(loginLayout, 800, 600);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px; -fx-cursor: hand;");
        button.setMaxWidth(200);
        return button;
    }

    private void showCustomerLoginDialog() {
        Dialog<ButtonType> dialog = createDialog("Customer Login");
        GridPane grid = createGridPane();

        TextField idField = new TextField();
        PasswordField passwordField = new PasswordField();

        grid.add(new Label("ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(passwordField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String id = idField.getText();
                String password = passwordField.getText();
                
                if (validateCustomerLogin(id, password)) {
                    createCustomerScene(findAccount(id));
                    primaryStage.setScene(customerScene);
                } else {
                    showAlert("Login Failed", "Invalid credentials!");
                }
            }
        });
    }

    private void createCustomerScene(Account account) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));
        layout.setStyle("-fx-background-color: #f4f4f4;");

        Label welcomeLabel = new Label("Welcome, " + account.getName());
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        welcomeLabel.setTextFill(Color.DARKBLUE);

        Label balanceLabel = new Label("Current Balance: $" + account.getBalance());
        balanceLabel.setFont(Font.font("Arial", 18));
        balanceLabel.setTextFill(Color.DARKGREEN);

        Button depositBtn = createStyledButton("Deposit");
        Button withdrawBtn = createStyledButton("Withdraw");
        Button transferBtn = createStyledButton("Transfer");
        Button logoutBtn = createStyledButton("Logout");

        depositBtn.setOnAction(e -> showDepositDialog(account, balanceLabel));
        withdrawBtn.setOnAction(e -> showWithdrawDialog(account, balanceLabel));
        transferBtn.setOnAction(e -> showTransferDialog(account, balanceLabel));
        logoutBtn.setOnAction(e -> primaryStage.setScene(loginScene));

        layout.getChildren().addAll(welcomeLabel, balanceLabel, depositBtn, withdrawBtn, transferBtn, logoutBtn);
        customerScene = new Scene(layout, 800, 600);
    }

    private void showDepositDialog(Account account, Label balanceLabel) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Deposit");
        dialog.setHeaderText("Enter amount to deposit:");
        dialog.setContentText("Amount: $");

        dialog.showAndWait().ifPresent(amount -> {
            try {
                double depositAmount = Double.parseDouble(amount);
                account.deposit(depositAmount);
                balanceLabel.setText("Current Balance: $" + account.getBalance());
                showAlert("Success", "Deposit successful!");
            } catch (NumberFormatException e) {
                showAlert("Error", "Please enter a valid amount!");
            }
        });
    }

    private void showWithdrawDialog(Account account, Label balanceLabel) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Withdraw");
        dialog.setHeaderText("Enter amount to withdraw:");
        dialog.setContentText("Amount: $");

        dialog.showAndWait().ifPresent(amount -> {
            try {
                double withdrawAmount = Double.parseDouble(amount);
                if (withdrawAmount <= account.getBalance()) {
                    account.withdraw(withdrawAmount);
                    balanceLabel.setText("Current Balance: $" + account.getBalance());
                    showAlert("Success", "Withdrawal successful!");
                } else {
                    showAlert("Error", "Insufficient funds!");
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Please enter a valid amount!");
            }
        });
    }

    private void showTransferDialog(Account sourceAccount, Label balanceLabel) {
        Dialog<ButtonType> dialog = createDialog("Transfer Money");
        GridPane grid = createGridPane();

        TextField recipientIdField = new TextField();
        TextField amountField = new TextField();

        grid.add(new Label("Recipient ID:"), 0, 0);
        grid.add(recipientIdField, 1, 0);
        grid.add(new Label("Amount:"), 0, 1);
        grid.add(amountField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    String recipientId = recipientIdField.getText();
                    double amount = Double.parseDouble(amountField.getText());
                    Account recipientAccount = findAccount(recipientId);
                    
                    if (recipientAccount != null) {
                        Account.transfer(sourceAccount, recipientAccount, amount);
                        balanceLabel.setText("Current Balance: $" + sourceAccount.getBalance());
                    } else {
                        showAlert("Error", "Recipient account not found!");
                    }
                } catch (NumberFormatException e) {
                    showAlert("Error", "Please enter a valid amount!");
                }
            }
        });
    }

    private void showAdminLoginDialog() {
        Dialog<ButtonType> dialog = createDialog("Admin Login");
        GridPane grid = createGridPane();

        TextField adminIdField = new TextField();
        PasswordField adminPasswordField = new PasswordField();

        grid.add(new Label("Admin ID:"), 0, 0);
        grid.add(adminIdField, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(adminPasswordField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String adminId = adminIdField.getText();
                String adminPassword = adminPasswordField.getText();
                
                Admin admin = new Admin();
                if (admin.AreYouAdmin(adminId, adminPassword, admins)) {
                    createAdminScene();
                    primaryStage.setScene(adminScene);
                } else {
                    showAlert("Login Failed", "Invalid admin credentials!");
                }
            }
        });
    }

    private void createAdminScene() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));
        layout.setStyle("-fx-background-color: #f4f4f4;");

        Label titleLabel = new Label("Admin Dashboard");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.DARKBLUE);

        Button viewCustomersBtn = createStyledButton("View All Customers");
        Button removeCustomerBtn = createStyledButton("Remove Customer");
        Button findAccountBtn = createStyledButton("Find Account");
        Button logoutBtn = createStyledButton("Logout");

        TextArea customerInfoArea = new TextArea();
        customerInfoArea.setEditable(false);
        customerInfoArea.setPrefRowCount(10);
        customerInfoArea.setWrapText(true);
        customerInfoArea.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 14px;");

        viewCustomersBtn.setOnAction(e -> {
            StringBuilder info = new StringBuilder("All Customers:\n\n");
            for (Customer customer : customers) {
                info.append(customer.toString()).append("\n");
            }
            customerInfoArea.setText(info.toString());
        });

        removeCustomerBtn.setOnAction(e -> showRemoveCustomerDialog(customerInfoArea));
        findAccountBtn.setOnAction(e -> showFindAccountDialog());
        logoutBtn.setOnAction(e -> primaryStage.setScene(loginScene));

        layout.getChildren().addAll(titleLabel, viewCustomersBtn, removeCustomerBtn, findAccountBtn, customerInfoArea, logoutBtn);
        adminScene = new Scene(layout, 800, 600);
    }

    private void showCreateAccountDialog() {
        Dialog<ButtonType> dialog = createDialog("Create New Account");
        GridPane grid = createGridPane();

        TextField nameField = new TextField();
        TextField addressField = new TextField();
        TextField phoneField = new TextField();
        TextField ageField = new TextField();
        TextField emailField = new TextField();
        PasswordField passwordField = new PasswordField();

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Address:"), 0, 1);
        grid.add(addressField, 1, 1);
        grid.add(new Label("Phone:"), 0, 2);
        grid.add(phoneField, 1, 2);
        grid.add(new Label("Age:"), 0, 3);
        grid.add(ageField, 1, 3);
        grid.add(new Label("Email:"), 0, 4);
        grid.add(emailField, 1, 4);
        grid.add(new Label("Password:"), 0, 5);
        grid.add(passwordField, 1, 5);

        dialog.getDialogPane().setContent(grid);

        dialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    String name = nameField.getText();
                    String address = addressField.getText();
                    String phone = phoneField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String email = emailField.getText();
                    String password = passwordField.getText();
                    
                    String id = name + (int)(Math.random() * 999 + 1);
                    
                    Account account = new Account(name, id, password);
                    Customer customer = new Customer(name, address, phone, id, age, email, 2024);
                    
                    accounts.add(account);
                    customers.add(customer);
                    
                    showAlert("Success", "Account created successfully!\nYour ID is: " + id);
                } catch (NumberFormatException e) {
                    showAlert("Error", "Please enter valid information!");
                }
            }
        });
    }

    private void showRemoveCustomerDialog(TextArea customerInfoArea) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Remove Customer");
        dialog.setHeaderText("Enter customer ID to remove:");
        dialog.setContentText("Customer ID:");

        dialog.showAndWait().ifPresent(id -> {
            Bank bank = new Bank();
            bank.removeCustomer(id, customers, accounts);
            
            StringBuilder info = new StringBuilder("All Customers:\n\n");
            for (Customer customer : customers) {
                info.append(customer.toString()).append("\n");
            }
            customerInfoArea.setText(info.toString());
        });
    }

    private void showFindAccountDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Find Account");
        dialog.setHeaderText("Enter account ID to find:");
        dialog.setContentText("Account ID:");

        dialog.showAndWait().ifPresent(id -> {
            Bank bank = new Bank();
            Account foundAccount = bank.findAccountById(id, accounts);
            
            if (foundAccount != null) {
                showAlert("Account Found", 
                    "Name: " + foundAccount.getName() + 
                    "\nID: " + foundAccount.getId() + 
                    "\nBalance: $" + foundAccount.getBalance());
            } else {
                showAlert("Error", "Account not found!");
            }
        });
    }

    private boolean validateCustomerLogin(String id, String password) {
        return Account.login(id, password, accounts);
    }

    private Account findAccount(String id) {
        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private Dialog<ButtonType> createDialog(String title) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.getDialogPane().setStyle("-fx-background-color: white;");
        return dialog;
    }

    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        return grid;
    }

    public static void main(String[] args) {
        launch(args);
    }
}