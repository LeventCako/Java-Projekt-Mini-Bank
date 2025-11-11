import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

public final class Geld implements Comparable<Geld> {  //Interface "Comparable<Geld>" zum Vergleichen von Objekten

    private final BigDecimal betrag;
    private final Currency waehrung;

    //Konstruktor
    public Geld(BigDecimal betrag, Currency waehrung){
        if (betrag == null) throw new NullPointerException("Betrag darf nicht null sein");      //Runtime-Exception Kapitel 10
        if (waehrung == null) throw new NullPointerException("Waehrung darf nicht null sein");  

        int fractionDigits = waehrung.getDefaultFractionDigits(); //Rundet nach 2 Kommastellen (nicht im Sktipt)

        this.betrag = betrag.setScale(fractionDigits, RoundingMode.HALF_EVEN); //RoundingMode.HALF_EVEN = Rundung bei Finanzen
        this.waehrung = waehrung;
        
        }

        //Getter
        public BigDecimal getBetrag(){
            return betrag;
        }

        public Currency getWaehrung(){
            return waehrung;
        }

        public Geld add(Geld other){                        
            ensureSameCurrency(other);                       //Überprüfung auf selbe Währung
            BigDecimal sum = this.betrag.add(other.betrag);  //Addiert zwei Geld-Objekte
            return new Geld(sum, this.waehrung);             //Gibt neues Geld-Objekt zurück
        }

        public Geld subtract(Geld other){
            ensureSameCurrency(other);                               //Überprüfung auf selbe Währung
            BigDecimal result = this.betrag.subtract(other.betrag);  //Subtrahiert zwei Geld-Objekte
            return new Geld(result, this.waehrung);                  //Gibt neues Geld-Objekt zurück
        }

        @Override                                         //Methode implementiert das Interface Comparable.compareTo
        public int compareTo(Geld other){
            ensureSameCurrency(other);                   //Überprüfung auf selbe Währung
            return this.betrag.compareTo(other.betrag);
        }

        private void ensureSameCurrency(Geld other){
            if (other == null) throw new NullPointerException("other darf nicht null sein");     //Defensive Nullprüfung, Runtime-Exeption Kapitel 10
            if(!this.waehrung.equals(other.waehrung)){                                             //Prüft Währungsgleichheit
                throw new IllegalArgumentException(
                    String.format("Unterschiedliche Währungen: %s vs %s",
                    this.waehrung.getCurrencyCode(), other.waehrung.getCurrencyCode()));
            }
        }

        @Override                               //Kennzeichnet Überschreibung von Object.equals Kapitel 7
        public boolean equals(Object obj){
            if (this == obj) return true;
            if(!(obj instanceof Geld)) return false;
            Geld other = (Geld) obj;                   //Typumwandlung
            return this.betrag.compareTo(other.betrag) == 0
                && this.waehrung.equals(other.waehrung);
        }

        @Override
        public int hashCode(){
            BigDecimal normalized = betrag.stripTrailingZeros(); //Entfernt unnötige Nullen
            return Objects.hash(normalized, waehrung);
        }

        @Override
        public String toString(){
                return String.format("%s %s", betrag.toPlainString(), waehrung.getCurrencyCode());
        }

}

//Getter Setter Kapitel 7 ?
//@Override
//Defensive Nullprüfung, Runtime-Exeption Kapitel 10
//Kennzeichnet Überschreibung von Object.equals Kapitel 7
