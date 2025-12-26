# Banking Management System (Java)

A **console-based Banking Management System** developed using **Core Java**.  
This project allows users to create and manage bank accounts with features like deposit, withdrawal, transfer, and data persistence using file handling.

---

## 📌 Project Overview

The Banking Management System is a menu-driven Java application designed to demonstrate:
- Object-Oriented Programming (OOP)
- Java Collections
- File Handling & Serialization
- Exception Handling

The application stores account data locally and restores it when restarted.

---

## 🚀 Features

- Create new bank accounts
- View account details
- Deposit money
- Withdraw money
- Transfer money between accounts
- Display all accounts
- Persistent data storage using file system
- Simple and user-friendly console menu

---

## 🛠️ Technologies Used

- Java (JDK 11 or above)
- VS Code
- Core Java concepts:
  - OOP (Encapsulation, Abstraction)
  - Collections (`HashMap`)
  - File Handling
  - Serialization
  - Exception Handling

---

## 📁 Project Structure

```
Banking-Management-System/
│
├── Account.java      # Account model
├── Bank.java         # Banking operations logic
├── DataStore.java    # File save & load logic
├── Main.java         # Application entry point
├── bank_data.db      # Auto-generated data file
└── README.md
```

---

## ⚙️ How to Run the Project

### Prerequisites
- Java JDK 11 or later
- VS Code (recommended)
- Extension Pack for Java (VS Code)

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/Vijay93902/Banking-Management-Sytem.git
   ```

2. Open the project folder in VS Code

3. Compile the Java files:
   ```bash
   javac *.java
   ```

4. Run the application:
   ```bash
   java Main
   ```

---

## 📋 Sample Menu

```
=== Banking Management System ===
1. Create account
2. View account
3. Deposit
4. Withdraw
5. Transfer
6. List all accounts
7. Save data
0. Exit
```

---

## 💾 Data Persistence

- All account data is saved in `bank_data.db`
- Data is automatically loaded when the application restarts

---

## 🔮 Future Enhancements

- MySQL database integration using JDBC
- Login system with PIN authentication
- Transaction history
- GUI using JavaFX or Swing
- Web application using Spring Boot

---

## 👨‍💻 Author

**Vijay Boya**  
GitHub: https://github.com/Vijay93902

---

## 📜 License

This project is created for **learning and educational purposes**.
