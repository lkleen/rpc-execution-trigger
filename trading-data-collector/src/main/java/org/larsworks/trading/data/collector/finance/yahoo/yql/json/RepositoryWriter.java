package org.larsworks.trading.data.collector.finance.yahoo.yql.json;

import org.larsworks.trading.data.repository.Company;
import org.larsworks.trading.data.repository.Repository;
import org.larsworks.trading.data.repository.Writer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LKLeen on 29.03.2017.
 */
public class RepositoryWriter implements Writer<Response> {

    private enum Result {
        created,
        appended
    }

    @Override
    public void store(Repository repository, Response response) {
        writeQuotes(repository, response);
    }

    public void writeQuotes(Repository repository, Response response) {
        Map<String, Company> companies = new HashMap<>();
        for (Quote quote : response.query.results.quote) {
            org.larsworks.trading.data.repository.Quote entry = createQuoteEntry(quote);
            Result result = createOrAppend(companies, quote.symbol,entry);
            if (result == Result.created) {
                repository.getCompanies().add(companies.get(quote.symbol));
            }
        }
    }

    private Result createOrAppend(Map<String, Company> companies, String symbol, org.larsworks.trading.data.repository.Quote entry) {
        Company company = companies.get(symbol);
        Result result = null;
        if (company == null) {
            result = Result.created;
            company = new Company();
        } else {
            result = Result.appended;
        }
        company.setSymbol(symbol);
        List<org.larsworks.trading.data.repository.Quote> quotes = company.getQuotes();
        quotes = quotes == null ? new ArrayList<>() : quotes;
        company.setQuotes(quotes);
        quotes.add(entry);
        companies.put(symbol, company);
        return result;
    }

    private org.larsworks.trading.data.repository.Quote createQuoteEntry(Quote quote) {
        org.larsworks.trading.data.repository.Quote entry =
                new org.larsworks.trading.data.repository.Quote();
        entry.setClose(Double.valueOf(quote.close));
        entry.setDate(LocalDate.parse(quote.date));
        entry.setHigh(Double.valueOf(quote.high));
        entry.setLow(Double.valueOf(quote.low));
        entry.setOpen(Double.valueOf(quote.open));
        entry.setVolume(Integer.valueOf(quote.volume));
        return entry;
    }

}
