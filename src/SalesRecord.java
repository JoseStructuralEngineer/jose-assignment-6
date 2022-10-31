import java.io.Serializable;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.IntStream;

public class SalesRecord  implements java.io.Serializable, Comparable<String>, CharSequence {

    LocalDate localDate;

    int quantitySold;
    String model;

    public SalesRecord(){
    }




    public SalesRecord(LocalDate localDate, String model, int quantitySold){
        this.localDate = localDate;
        this.model = model;
        this.quantitySold = quantitySold;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesRecord that = (SalesRecord) o;
        return localDate == that.localDate && model == that.model && quantitySold == that.quantitySold;
    }

    @Override
    public int hashCode() {
        return Objects.hash(localDate, model, quantitySold);
    }



    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public String getModel() {return model; }

    public void setModel(String model) {this.model = model;}

    @Override
    public int length() {
        return 4;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }

    @Override
    public IntStream chars() {
        return CharSequence.super.chars();
    }

    @Override
    public IntStream codePoints() {
        return CharSequence.super.codePoints();
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    public String toUpperCase() {
        String toReturn = "";
        return "Perro Loco";
    }
}
