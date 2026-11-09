import java.util.HashMap;
import javax.swing.JOptionPane;
import java.util.ArrayList;

class SavingsAccount extends BankAccount {

    SavingsAccount(HashMap<String, ArrayList<Object>> accounts){
        super(accounts);
    }

    public void savingsDeposit(String email){

        ArrayList<Object> details = accounts.get(email);

        double savingsBalance = (double) details.get(2);
        double checkingBalance = (double) details.get(1);

        String input = JOptionPane.showInputDialog(null, "Enter Deposit Amount: ", "Input", JOptionPane.QUESTION_MESSAGE);
        double amount = Double.parseDouble(input);

        if (amount > checkingBalance){
            JOptionPane.showMessageDialog(null, "Insufficient Funds", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double newCheckingBalance = checkingBalance - amount;
        double newSavingsBalance = savingsBalance + amount;

        details.set(1, newCheckingBalance);
        details.set(2, newSavingsBalance);

    }

    public void savingsWithdrawal(String email){

        ArrayList<Object> details = accounts.get(email);

        double checkingBalance = (double) details.get(1);
        double savingsBalance = (double) details.get(2);

        String input = JOptionPane.showInputDialog(null, "Enter Withdrawal Amount: ", "Input", JOptionPane.QUESTION_MESSAGE);
        double amount = Double.parseDouble(input);

        if (amount > savingsBalance){
            JOptionPane.showMessageDialog(null, "Insufficient Funds", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double newCheckingBalance = checkingBalance + amount;
        double newSavingsBalance = savingsBalance - amount;

        details.set(1, newCheckingBalance);
        details.set(2, newSavingsBalance);

    }

    public void displayAccounts(String email){

        ArrayList<Object> details = accounts.get(email);

        double checkingAccount = (double) details.get(1);
        double savingsAccount = (double) details.get(2);

        JOptionPane.showMessageDialog(null, "Checking Account : " + 
        checkingAccount + "\nSavings Account : " + savingsAccount, "Balances", 
        JOptionPane.INFORMATION_MESSAGE);
    }

    public void displaySavings(String email){

        ArrayList<Object> details = accounts.get(email);

        double savingsAccount = (double) details.get(2);

        JOptionPane.showMessageDialog(null, "Savings Account : " + savingsAccount, "Savings", JOptionPane.INFORMATION_MESSAGE);
    }
}

class BankAccount {

    protected HashMap<String, ArrayList<Object>> accounts;

    BankAccount(HashMap<String, ArrayList<Object>> accounts){
        this.accounts = accounts;
    }

    public void createAccount() {

        String email = JOptionPane.showInputDialog(null, "Enter an email: ", "Input", JOptionPane.QUESTION_MESSAGE);

        if (accounts.containsKey(email) || !email.contains("@") || !email.endsWith(".com")){
            JOptionPane.showMessageDialog(null, "Invalid Email", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String password = JOptionPane.showInputDialog(null, "Enter a password: ", "Input", JOptionPane.QUESTION_MESSAGE);
        
        if (password.length() < 8 || password == null){
            JOptionPane.showMessageDialog(null, "Invalid Password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = JOptionPane.showInputDialog(null, "Enter a name: ", "Input", JOptionPane.QUESTION_MESSAGE);

        ArrayList<Object> details = new ArrayList<>();

        details.add(name);
        details.add(0.0);
        details.add(0.0);
        details.add(password);

        accounts.put(email, details);
    }

    public String login(){

        String email = null;

        try {

            email = JOptionPane.showInputDialog(null, "Enter An Email: ", "Input", JOptionPane.QUESTION_MESSAGE);

            if (!accounts.containsKey(email)){
                JOptionPane.showMessageDialog(null, "Account Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                return login();
            }

            ArrayList<Object> details = accounts.get(email);

            String userPassword = (String) details.get(3);

            String password = JOptionPane.showInputDialog(null, "Enter your password: ", "Input", JOptionPane.QUESTION_MESSAGE);

            if (!password.equals(userPassword)){
                JOptionPane.showMessageDialog(null, "Incorrect Password", "Error", JOptionPane.ERROR_MESSAGE);
                return login();
            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error : " + error, "Error", JOptionPane.ERROR_MESSAGE);
        }

        return email;
    }

    public void deposit(String email){

        ArrayList<Object> details = accounts.get(email);

        try {

            String input = JOptionPane.showInputDialog(null, "Enter Deposit Amount: ", "Input", JOptionPane.QUESTION_MESSAGE);

            double amount = Double.parseDouble(input);

            double balance = (double) details.get(1);

            double newBalance = amount + balance;

            details.set(1, newBalance);

            JOptionPane.showMessageDialog(null, "New Balance : " + newBalance, "New Balance", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error : " + error, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void withdraw(String email){


        ArrayList<Object> details = accounts.get(email);

        try {

            String input = JOptionPane.showInputDialog(null, "Enter Withdrawal Amount: ", "Input", JOptionPane.QUESTION_MESSAGE);

            double balance = (double) details.get(1);

            double amount = Double.parseDouble(input);

            if (amount > balance){
                JOptionPane.showMessageDialog(null, "Insufficient funds", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double newBalance = balance - amount;

            details.set(1, newBalance);

            JOptionPane.showMessageDialog(null, "New Balance : " + newBalance, "New Balance", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error : " + error, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void getAccounts(String email){
        SavingsAccount savingsAccount = new SavingsAccount(accounts);

        savingsAccount.displayAccounts(email);
    }

    public void getSavingsAccount(String email){
        SavingsAccount savingsAccount = new SavingsAccount(accounts);

        savingsAccount.displaySavings(email);
    }

    public void getSavingsDeposit(String email){
        SavingsAccount savingsAccount = new SavingsAccount(accounts);

        savingsAccount.savingsDeposit(email);
    }

    public void getSavingsWithdrawal(String email){
        SavingsAccount savingsAccount = new SavingsAccount(accounts);

        savingsAccount.getSavingsWithdrawal(email);
    }

    public void displayChecking(String email){

        ArrayList<Object> details = accounts.get(email);

        double balance = (double) details.get(1);

        JOptionPane.showMessageDialog(null, "Checking Account : " + balance, "Checking", JOptionPane.INFORMATION_MESSAGE);
    }

    public void logout(){
        JOptionPane.showMessageDialog(null, "Logging Out", "Goodbye", JOptionPane.INFORMATION_MESSAGE);
    }

}

public class Bank {

    public static void main(String[] args){

        HashMap<String, ArrayList<Object>> accounts = new HashMap<>();
        BankAccount bank = new BankAccount(accounts);

        while (true){

            try {

                String input = JOptionPane.showInputDialog(null, 
                    "1. Create Account\n2. Login\n3. Exit");

                int choice = Integer.parseInt(input);

                if (choice == 1){

                    bank.createAccount();

                }
                else if (choice == 2){

                    String email = bank.login();

                    options(bank, email);

                }
                
                else if (choice == 3){
                    break;
                }
                else {

                    JOptionPane.showMessageDialog(null, "Invalid Option", "Error", JOptionPane.ERROR_MESSAGE);

                }

            } catch (Exception error){
                JOptionPane.showMessageDialog(null, "Error : " + error, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void options(BankAccount bank, String email){

        while (true){

            String choices = JOptionPane.showInputDialog(null, 
            "1. Deposit\n2. Withdraw\n3. Deposit Savings\n4. Withdraw Savings\n5. Display All Accounts\n6. Display Checking\n7. Display Savings\n 8. Log Out");

            int userInput = Integer.parseInt(choices);

            if (userInput == 1){
                bank.deposit(email);
            }
            else if (userInput == 2){
                bank.withdraw(email);
            }
            else if (userInput == 3){
                bank.getSavingsDeposit(email);
            }
            else if (userInput == 4){
                bank.getSavingsWithdrawal(email);
            } 
            else if (userInput == 5){
                bank.getAccounts(email);
            }
            else if (userInput == 6){
                bank.displayChecking(email);
            }
            else if (userInput == 7){
                bank.getSavingsAccount(email);
            }
            else if (userInput == 8){
                bank.logout();
                break;
            }
            else {
                JOptionPane.showMessageDialog(null, "Invalid Input", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}