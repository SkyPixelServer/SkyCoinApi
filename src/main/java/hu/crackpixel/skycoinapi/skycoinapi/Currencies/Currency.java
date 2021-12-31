package hu.crackpixel.skycoinapi.skycoinapi.Currencies;

import java.util.HashMap;

public class Currency {
    private String CurrencyName; // the name of the currency, like "Coin"
    private float Amount = 0; // The amount of the currency the object has.

    /* self explanatory */
    public String getName() {
        return CurrencyName;
    }

    public void setCurrencyName(String currencyName) {
        CurrencyName = currencyName;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }

    public void setAmount(int amount) {
        Amount = (float)amount;
    }

    public void setAmount(double amount) {
        Amount = (float)amount;
    }

    public void addAmount(float amount) {
        Amount += amount;
    }

    public void addAmount(double amount) {
        Amount += (float)amount;
    }

    public void subtractAmount(float amount) {
        Amount -= amount;
    }

    public void subtractAmount(double amount) {
        Amount -= (float)amount;
    }


    public Currency(String currencyName, float amount) {
        CurrencyName = currencyName;
        Amount = amount;
    }



}
