# Banking System

A simple desktop banking app written in Java. It uses Swing `JOptionPane` dialogs for all input and output, so there is no console interaction after launch.

## Features

- Create an account with an email, password, and name
- Log in with email and password
- Checking account: deposit, withdraw, view balance
- Savings account: transfer money between checking and savings, view balance
- View both balances at once

## Requirements

- Java Development Kit (JDK) 8 or newer
- A desktop environment (Swing dialogs need a display)

## Build and Run

```bash
javac Bank.java
java Bank
```

## Usage

**Main menu**

| Option | Action |
|--------|--------|
| 1 | Create Account |
| 2 | Login |
| 3 | Exit |

**Account menu (after login)**

| Option | Action |
|--------|--------|
| 1 | Deposit into checking |
| 2 | Withdraw from checking |
| 3 | Deposit to savings (moves money from checking) |
| 4 | Withdraw from savings (moves money to checking) |
| 5 | Display all account balances |
| 6 | Display checking balance |
| 7 | Display savings balance |
| 8 | Log out |

**Account rules**

- Email must contain `@` and end with `.com`, and must not already be registered.
- Password must be at least 8 characters.
- New accounts start with a $0.00 checking and savings balance.
- Savings transfers are rejected if the source account has insufficient funds.

## Project Structure

Everything lives in [Bank.java](Bank.java):

| Class | Role |
|-------|------|
| `Bank` | Entry point; main menu and account menu loops |
| `BankAccount` | Account creation, login, checking deposit/withdraw, and helpers that delegate to savings |
| `SavingsAccount` | Extends `BankAccount`; savings transfers and balance display |

Accounts are stored in memory as `HashMap<String, ArrayList<Object>>`, keyed by email. Each value holds `[name, checkingBalance, savingsBalance, password]`.
