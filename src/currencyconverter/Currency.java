
package currencyconverter;

public class Currency implements Comparable{
    String country;
    String currency;
    double tocountry;
    double fromcountry;
    public Currency(){
    }
    public Currency(String country, String currency, double tocountry, double fromcountry){
        this.country = country;
        this.currency = currency;
        this.tocountry = tocountry;
        this.fromcountry = fromcountry;
    }

    @Override
    public int compareTo(Object o) {
        return this.country.compareTo(((Currency) o).country);
    }
}
