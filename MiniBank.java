import java.util.Scanner;

class Konto {
    private String inhaber;
    private double kontostand;

    public Konto(String inhaber, double startBetrag) {
        this.inhaber = inhaber;
        this.kontostand = startBetrag;
    }

    public double getKontostand() {
        return kontostand;
    }

    public String getInhaber() {
        return inhaber;
    }

    public void einzahlen(double betrag) {
        if (betrag > 0) {
            kontostand += betrag;
            System.out.printf("%.2f$ erfolgreich eingezahlt.%n", betrag);
        } else {
            System.out.println("Ungültiger Betrag!");
        }
    }

    public void auszahlen(double betrag) {
        if (betrag > 0 && betrag <= kontostand) {
            kontostand -= betrag;
            System.out.printf("%.2f$ erfolgreich ausgezahlt.%n", betrag);
        } else {
            System.out.println("Ungültiger Betrag oder nicht genug Geld!");
        }
    }
}

public class MiniBank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Willkommen bei der Mini-Bank!");
        System.out.print("Bitte gib deinen Namen ein: ");
        String name = scanner.nextLine();

        Konto meinKonto = new Konto(name, 0);

        boolean weiter = true;

        while (weiter) {
            System.out.println("\n1: Kontostand anzeigen");
            System.out.println("2: Einzahlen");
            System.out.println("3: Auszahlen");
            System.out.println("4: Beenden");
            System.out.print("Wähle eine Option: ");
            
            String eingabe = scanner.nextLine();
            int auswahl;
            try {
                auswahl = Integer.parseInt(eingabe);
            } catch (NumberFormatException e) {
                System.out.println("Bitte eine gültige Zahl eingeben!");
                continue;
            }

            switch (auswahl) {
                case 1:
                    System.out.printf("%s, dein Kontostand beträgt: %.2f$%n", meinKonto.getInhaber(), meinKonto.getKontostand());
                    break;
                case 2:
                    System.out.print("Betrag zum Einzahlen: ");
                    String einzahlStr = scanner.nextLine().replace(',', '.'); // Komma ersetzen
                    try {
                        double betrag = Double.parseDouble(einzahlStr);
                        meinKonto.einzahlen(betrag);
                    } catch (NumberFormatException e) {
                        System.out.println("Ungültiger Betrag!");
                    }
                    break;
                case 3:
                    System.out.print("Betrag zum Auszahlen: ");
                    String auszahlenStr = scanner.nextLine().replace(',', '.'); // Komma ersetzen
                    try {
                        double betrag = Double.parseDouble(auszahlenStr);
                        meinKonto.auszahlen(betrag);
                    } catch (NumberFormatException e) {
                        System.out.println("Ungültiger Betrag!");
                    }
                    break;
                case 4:
                    weiter = false;
                    System.out.println("Auf Wiedersehen, " + meinKonto.getInhaber() + "!");
                    break;
                default:
                    System.out.println("Ungültige Auswahl!");
            }
        }

        scanner.close();
    }
}
