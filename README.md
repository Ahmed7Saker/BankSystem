# Banking System Project

A simple Java-based banking system that allows users to create accounts, log in, perform transactions (deposit, withdraw, transfer), and manage their accounts. Admins can manage customers and accounts.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Classes Overview](#classes-overview)
- [Contributing](#contributing)
- [License](#license)
## Features

### User Features
- **Create Account**: Users can create a new account by providing their name, address, phone, age, email, and password.
- **Login**: Users can log in using their ID and password.
- **Deposit**: Users can deposit money into their account.
- **Withdraw**: Users can withdraw money from their account.
- **Check Balance**: Users can view their current balance.
- **Change Password**: Users can change their account password.
- **Transfer Money**: Users can transfer money to another account.

### Admin Features
- **Admin Login**: Admins can log in using their admin ID and password.
- **View All Customers**: Admins can view all registered customers.
- **Remove Customer**: Admins can remove a customer and their associated account.
- **Find Account by ID**: Admins can search for an account using the account ID.

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Ahmed7Saker/BankSystem.git
   cd BankSystem


### How to Use
1. Copy the above code.
2. Create a file named `README.md` in your project directory.
3. Paste the code into the `README.md` file.
4. Save the file.

### Key Additions
1. **GUI Version with JavaFX**: Added a section describing the GUI version of the project.
2. **AI Tools Mention**: Acknowledged the use of AI tools as a learning aid for implementing the GUI.
3. **How to Run the GUI Version**: Provided instructions for running the JavaFX application.
### Main Menu
![Screenshot](imgs/Screenshot%202025-01-27%20184616.png)

### Login Screen
![Screenshot](imgs/Screenshot%202025-01-27%20185113.png)

### Account Dashboard
![Screenshot](imgs/Screenshot%202025-01-27%20185200.png)
### Craeting account and sign up
![Screenshot](imgs/Screenshot%202025-01-27%20190550.png)

## Folder Structure
- Project Structure
 -  CopyBankSystem/
 -  ├── src/         # Source files
  - ├── lib/         # Dependencies
  - ├── bin/         # Compiled files
  - └── imgs/        # Application screenshots
  - Meanwhile, the compiled output files will be generated in the `bin` folder by default.
## Dependencies
- JavaFX (for GUI)
- JDBC (for database connectivity)
- Additional dependencies are managed in the `lib ` folder

## Contributing
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit them (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

## Classes Overview
The banking system is structured into several key classes:

### **Account**
The `Account` class represents a user's bank account and holds information like the account holder's name, balance, and transaction history. It includes methods for:
- **Deposit**: Adds money to the account balance.
- **Withdrawal**: Removes money from the account balance, ensuring sufficient funds.
- **Transfer**: Transfers money from this account to another account.
- **Check balance**: Displays the current account balance.
- **Change password**: Allows users to update their account password.
- **Account Creation**: Allows users to create a new account and link it to their user profile.


### **Customer**
The `Customer` class manages the basic information of each user, including:
- **Login credentials**: Stores the username and password of the user.
- **Account creation**: Allows users to create a new account and link it to their user profile.
- **Personal details**: Manages other user details such as name, address, and contact information.


### **Admin**
The `Admin` class handles administrative tasks such as:
- **Admin login**: Allows admins to log into the admin panel using special credentials.
- **View customer accounts**: Admins can view details of all registered customer accounts.
- **Remove customers**: Admins have the ability to remove a customer and their associated account from the system.
- **Find account by ID**: Admins can search for specific accounts using the account ID.
### **Bank**
The `Bank` class manages the overall functionality of the banking system, including:
- **View customer accounts**: Admins can view details of all registered customer accounts.
- **Remove customers**: Admins have the ability to remove a customer and their associated account from the system.
- **Find account by ID**: Admins can search for specific accounts using the account ID.
### **Bank**

### **Main**
The `Main` class acts as the main control center for the system, providing the menu for both users and admins to interact with the system. It includes:
- **Menu navigation**: Displays options for users and admins to choose actions such as creating an account, logging in, and managing accounts.
- **System control**: Handles the logic flow of the application, directing users and admins to the relevant features.

### **App**
The `App` class serves as the entry point for the application. It initializes the system and starts the main menu, allowing users to choose between user and admin modes with simple gui using javaFX.
### License
-This project is open source and available under standard open source terms.


<!-- 
> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

<!-- ## Dependency  -->Management
<!-- 
The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
 --> -->