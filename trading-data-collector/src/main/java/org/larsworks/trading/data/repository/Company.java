package org.larsworks.trading.data.repository;

import java.util.List;

/**
 * Created by LKLeen on 29.03.2017.
 */
public class Company {
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    String symbol;
    List<Quote> quotes;
}
