import java.util.ArrayList;
import java.util.Scanner;

class User {
    private int accountNumber;
  private  int pin;
   private int balance;

    public User(int accountNumber, int balance, int pin) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
    }
    public User setBalance(int b){
       this.balance=b;
        return this;
    }

    public User setPin(int pin) {
        this.pin = pin;
        return this;
    }

    public User setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public int getPin() {
        return pin;
    }
}
////
class ATM {
    User currentUser;
    Scanner x = new Scanner(System.in);

    public boolean login(User user) {
        System.out.println("enter PIN: ");
        Scanner x = new Scanner(System.in);
        int attempt = 0;
        while (attempt < 3) {

            int pin = x.nextInt();

            if (pin == user.getPin()) {
                System.out.println(":)WELCOME..");
                currentUser = user;
                return true;
            }
            if(attempt==2){
                System.out.println("***TOO MANY FAILED ATTEMPTS***\n ");

                System.out.println("******************************");
                System.out.println("    Better Luck Next Time :(  ");
                System.out.println("******************************");

                break;
            }

            System.out.println("---incorrect PIN...try again "+(3-(attempt+1))+" attempt left---");
            System.out.println("---------------------------------------------------------------------------");

               attempt++;;
        }

        return false;
    }

    public int checkBalance() {
        return currentUser.getBalance();
    }

    public void deposit() {
        System.out.println("enter amount to deposit: ");

        int amount = x.nextInt();
        int balance = currentUser.getBalance();
        if (amount > 0) {
            balance += amount;
            currentUser.setBalance(balance);

        }
    }

    public boolean withdraw() {
        System.out.println("enter amount to withdraw: ");

        int amount = x.nextInt();

        int balance = currentUser.getBalance();

        if (amount <= balance) {
            balance -= amount;
            currentUser.setBalance(balance);
        } else System.out.println("\nxx insufficient balance xx\n");
return false;
    }

    public void showMenu() {


        int choice = 0;
        while (choice != 4) {
            if(choice==2 || choice==3){
                System.out.println("Updated Balance= "+currentUser.getBalance()+"");
            }
            System.out.println("*********************************************************************************************");
            System.out.println("\nPRESS \n1: Check Balance\n2: Deposit\n3: Withdraw\n4: EXIT\n");
            choice = x.nextInt();
            System.out.println();
            switch (choice) {
                //check balance
                case 1:
                    System.out.println("Balance= " + currentUser.getBalance());
                    break;
                case 2:
                    deposit();

                    System.out.println("transaction is completed\n\n");
                    break;
                case 3:


                    if(withdraw()==true){
                    System.out.println("transaction is completed\n\n");}
                    break;
                case 4:
                    System.out.println("Exited.....\nThankyou please visit again..");
                    break;
                default:
                    System.out.println("invalid choice...please enter again");
            }


        }

    }
}
////
public class ATMmain{

public static void main(String[] args) {
    Scanner x = new Scanner(System.in);
    User user1 = new User(23212323, 19222, 1234);
    User user2 = new User(32467532, 100200, 5678);
    User user3 = new User(23962467, 2001200, 9898);
    User user4 = new User(23566542, 19000010, 1224);
    ArrayList<User> a = new ArrayList<>();
    a.add(user1);
    a.add(user2);
    a.add(user3);
    a.add(user4);
    System.out.println("enter Account number: ");
    int account = x.nextInt();
    ATM session = new ATM();
    User matched = null;
    for (User u : a) {
        if (u.getAccountNumber() == account) {
            matched = u;
            break;
        }

    }
    if (matched == null) {
        System.out.println("X Account not found");
        System.out.println();
        System.exit(0);
    }

if(session.login(matched)==true) session.showMenu();

}
}
