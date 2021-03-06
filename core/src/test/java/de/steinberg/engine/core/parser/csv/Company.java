package de.steinberg.engine.core.parser.csv;

import lombok.Data;

/**
 * Created by lars on 29.01.2017.
 */
@Data
public class Company {

    String symbol = "Symbol";
    String name = "Name";
    String lastSale = "LastSale";
    String MarketCap = "MarketCap";
    String adr_tso = "ADR TSO";
    String iPOyear = "IPOyear";
    String sector ="Sector";
    String industry = "Industry";
    String summary_quote = "Summary Quote";

}
