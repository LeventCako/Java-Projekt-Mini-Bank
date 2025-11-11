import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class lukas {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        BigDecimal balance = BigDecimal.ZERO;
        boolean isRunning = true;
        int choice;
        String username;

        // Ask for user details
        System.out.print("Geben Sie Ihren Namen ein: ");
        username = scanner.nextLine();

        while(isRunning){
            System.out.println("\n***************************************");
            System.out.println("Willkommen in deinem Online-Banking, " + username + ".");
            System.out.println("***************************************");
            System.out.println("Wähle deine Option:");
            System.out.println("1. Kontostand");
            System.out.println("2. Einzahlung");
            System.out.println("3. Auszahlung");
            System.out.println("4. Überweisung");
            System.out.println("5. Beenden");

            System.out.print("Enter your choice (1-5): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } else {
                System.out.println("Ungültige Eingabe. Gebe eine Zahl von 1 bis 5 ein.");
                scanner.nextLine();
                continue;
            }

            switch(choice){
                case 1 -> showBalance(balance);
                case 2 -> balance = balance.add(deposit());
                case 3 -> balance = balance.subtract(withdraw(balance));
                case 4 -> balance = transfer(balance);
                case 5 -> isRunning = false;
                default -> System.out.println("Ungültige Eingabe. Geben Sie bitte eine Zahl von 1 bis 5 ein.");
            }
        }

        System.out.println("\n***************************");
        System.out.println("Danke, " + username + "! bis zum nächsten Mal!");
        System.out.println("***************************");

        scanner.close();
    }

    // Show the current balance
    static void showBalance(BigDecimal balance){
        System.out.println("***************");
        System.out.printf("Dein Kontostand: $%.2f%n", balance.setScale(2, RoundingMode.HALF_UP));
    }

    // Deposit money into the account
    static BigDecimal deposit(){
        System.out.print("Gebe den Betrag zum Einzahlen ein: ");
        while (!scanner.hasNextBigDecimal()) {
            System.out.println("Ungültige Eingabe.");
            scanner.next(); // clear the invalid input
        }
        BigDecimal amount = scanner.nextBigDecimal();

        if(amount.compareTo(BigDecimal.ZERO) < 0){
            System.out.println("Der Einzahlungsbetrag kann nicht negativ sein.");
            return BigDecimal.ZERO;
        } else {
            return amount;
        }
    }

    // Withdraw money from the account
    static BigDecimal withdraw(BigDecimal balance){
        System.out.print("Gebe den Betrag zum Auszahlen ein: ");
        while (!scanner.hasNextBigDecimal()) {
            System.out.println("Ungültige Eingabe.");
            scanner.next(); // clear the invalid input
        }
        BigDecimal amount = scanner.nextBigDecimal();

        if(amount.compareTo(balance)> 0){
            System.out.println("INSUFFICIENT FUNDS.");
            return BigDecimal.ZERO;
        } else if(amount.compareTo(BigDecimal.ZERO) < 0){
            System.out.println("Der Betrag darf nicht negativ sein");
            return BigDecimal.ZERO;
        } else {
            return amount;
        }
    }

    // Transfer money to another account (simulating simple transfer between accounts)
    static BigDecimal transfer(BigDecimal balance){
        BigDecimal amount;
        System.out.print("Gebe den Betrag zum Überweisen ein: ");
        while (!scanner.hasNextBigDecimal()) {
            System.out.println("Ungültige Eingabe.");
            scanner.next(); // clear the invalid input
        }
        amount = scanner.nextBigDecimal();

        if (amount.compareTo(balance) > 0) {
            System.out.println("INSUFFICIENT FUNDS.");
            return balance;  // No transfer, balance stays the same
        } else if(amount.compareTo(BigDecimal.ZERO) < 0){
            System.out.println("Der Betrag darf nicht negativ sein.");
            return balance;  // No transfer, balance stays the same
        } else {
            System.out.println("Überweisung erfolgreich. Es wurden: $" + amount + " überwiesen.");
            return balance.subtract(amount);  // Deduct transfer amount
        }
    }
}


//Optimierungen: dass man auch in minus geben kann, Dass Kontostand nach jeder Transaktion angezeigt wird.