import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        double balance = 0;
        boolean isRunning = true;
        int choice;
        String username;

        // Ask for user details
        System.out.print("Enter your name: ");
        username = scanner.nextLine();

        while(isRunning){
            System.out.println("\n***************");
            System.out.println("Welcome " + username + " to your Banking Programm!");
            System.out.println("***************");
            System.out.println("Choose your Option:");
            System.out.println("1. Show Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.println("***************");

            System.out.print("Enter your choice (1-5): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } else {
                System.out.println("INVALID INPUT! Please enter a number between 1 and 5.");
                scanner.nextLine();
                continue;
            }

            switch(choice){
                case 1 -> showBalance(balance);
                case 2 -> balance += deposit();
                case 3 -> balance -= withdraw(balance);
                case 4 -> balance = transfer(balance);
                case 5 -> isRunning = false;
                default -> System.out.println("INVALID CHOICE. Please enter a number between 1 and 5.");
            }
        }

        System.out.println("\n***************************");
        System.out.println("Thank you, " + username + "! Have a nice day!");
        System.out.println("***************************");

        scanner.close();
    }

    // Show the current balance
    static void showBalance(double balance){
        System.out.println("***************");
        System.out.printf("Your balance: $%.2f\n", balance);
    }

    // Deposit money into the account
    static double deposit(){
        double amount;
        System.out.print("Enter an amount to be deposited: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("INVALID AMOUNT. Please enter a valid number.");
            scanner.next(); // clear the invalid input
        }
        amount = scanner.nextDouble();

        if(amount < 0){
            System.out.println("Amount can't be negative.");
            return 0;
        } else {
            return amount;
        }
    }

    // Withdraw money from the account
    static double withdraw(double balance){
        double amount;
        System.out.print("Enter amount to be withdrawn: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("INVALID AMOUNT. Please enter a valid number.");
            scanner.next(); // clear the invalid input
        }
        amount = scanner.nextDouble();

        if(amount > balance){
            System.out.println("INSUFFICIENT FUNDS.");
            return 0;
        } else if(amount < 0){
            System.out.println("Amount can't be negative.");
            return 0;
        } else {
            return amount;
        }
    }

    // Transfer money to another account (simulating simple transfer between accounts)
    static double transfer(double balance){
        double amount;
        System.out.print("Enter amount to be transferred: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("INVALID AMOUNT. Please enter a valid number.");
            scanner.next(); // clear the invalid input
        }
        amount = scanner.nextDouble();

        if(amount > balance){
            System.out.println("INSUFFICIENT FUNDS.");
            return balance;  // No transfer, balance stays the same
        } else if(amount < 0){
            System.out.println("Amount can't be negative.");
            return balance;  // No transfer, balance stays the same
        } else {
            System.out.println("Transfer successful. Amount transferred: $" + amount);
            return balance - amount;  // Deduct transfer amount
        }
    }
}


//Optimierungen: dass man auch in minus geben kann, Dass Kontostand nach jeder Transaktion angezeigt wird.