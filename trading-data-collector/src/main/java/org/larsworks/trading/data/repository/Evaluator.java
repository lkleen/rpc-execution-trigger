package org.larsworks.trading.data.repository;

import jdk.nashorn.internal.runtime.QuotedStringTokenizer;
import org.larsworks.trading.data.collector.engine.query.generation.SelectQueryParameters;
import org.larsworks.trading.data.collector.engine.query.generation.SelectQueryRange;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LKLeen on 29.03.2017.
 */
public class Evaluator {

    static class SelectQueryRanges {
        public SelectQueryRange before;
        public SelectQueryRange after;
    }

    public List<SelectQueryParameters> getMissingEntries(
            Repository repository,
            LocalDate start,
            LocalDate end,
            String... symbols
    ) {
        List<SelectQueryParameters> result = new ArrayList<>();
        for (String symbol : symbols) {
            List<SelectQueryParameters> parameters = createParametersFor(repository, symbol, start, end);
            result.addAll(parameters);
        }
        return result;
    }

    private List<SelectQueryParameters> createParametersFor(Repository repository, String symbol, LocalDate start, LocalDate end) {
        Company company = getCompany(repository, symbol);
        if(company == null) {
            company = new Company();
            company.setSymbol(symbol);
            company.setQuotes(new ArrayList<>());
        }
        List<SelectQueryParameters> result = new ArrayList<>();
        List<Quote> quotes = company.getQuotes();
        if (quotes.size() == 0) {
            SelectQueryRange range = new SelectQueryRange(start, end);
            result.add(new SelectQueryParameters(symbol, range));
        } else {
            result.addAll(createQueryParametersForCompany (company, start, end));
        }
        return result;
    }

    private List<SelectQueryParameters> createQueryParametersForCompany(Company company, LocalDate start, LocalDate end) {
        SelectQueryRanges ranges = createRanges(company, start, end);
        List<SelectQueryParameters> parameters = new ArrayList<>();
        parameters.add(new SelectQueryParameters(company.getSymbol(), ranges.before));
        parameters.add(new SelectQueryParameters(company.getSymbol(), ranges.after));
        return parameters;
    }

    private SelectQueryRanges createRanges(Company company, LocalDate start, LocalDate end) {
        LocalDate companyStart = getStartDate(company);
        LocalDate companyEnd = getEndDate(company);
        SelectQueryRanges ranges = new SelectQueryRanges();
        ranges.after = getAfterRange(end, companyEnd);
        ranges.before = getBeforeRange(start, companyStart);
        return ranges;
    }

    private SelectQueryRange getAfterRange(LocalDate end, LocalDate companyEnd) {
        if (end.isBefore(companyEnd)) {
            return null;
        } else {
            return new SelectQueryRange(companyEnd, end);
        }
    }

    private SelectQueryRange getBeforeRange(LocalDate start, LocalDate companyStart) {
        if (start.isAfter(companyStart)) {
            return null;
        } else {
            return new SelectQueryRange(start, companyStart);
        }
    }

    private LocalDate getEndDate(Company company) {
        int size = company.getQuotes().size();
        if(size == 0) {
            return null;
        } else {
            return company.getQuotes().get(size - 1).getDate();
        }
    }

    private LocalDate getStartDate(Company company) {
        try {
            return company.getQuotes().get(0).getDate();
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Company getCompany(Repository repository, String symbol) {
        for (Company company : repository.getCompanies()) {
            if (company.getSymbol().equals(symbol)) {
                return company;
            }
        }
        return null;
    }
}
