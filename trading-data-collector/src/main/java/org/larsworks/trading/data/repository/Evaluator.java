package org.larsworks.trading.data.repository;

import org.larsworks.trading.data.collector.engine.query.generation.SelectQueryParameters;
import org.larsworks.trading.data.collector.engine.query.generation.SelectQueryRange;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LKLeen on 29.03.2017.
 */
public class Evaluator {

    public static class CompanyQueryData {
        public Company company;
        public SelectQueryRange before;
        public SelectQueryRange after;
    }

    public List<CompanyQueryData> getMissingEntries(
            Repository repository,
            LocalDate start,
            LocalDate end,
            String... symbols
    ) {
        List<CompanyQueryData> result = new ArrayList<>();
        for (String symbol : symbols) {
            CompanyQueryData companyQueryData = createParametersFor(repository, symbol, start, end);
            result.add(companyQueryData);
        }
        return result;
    }

    private CompanyQueryData createParametersFor(Repository repository, String symbol, LocalDate start, LocalDate end) {
        Company company = getCompany(repository, symbol);
        if(company == null) {
            company = new Company();
            company.setSymbol(symbol);
            company.setQuotes(new ArrayList<>());
        }
        List<Quote> quotes = company.getQuotes();
        if (quotes.size() == 0) {
            SelectQueryRange range = new SelectQueryRange(start, end);
            CompanyQueryData queryData = new CompanyQueryData();
            queryData.company = company;
            queryData.before = range;
            queryData.after = null;
            return queryData;
        } else {
            return createQueryParametersForCompany (company, start, end);
        }
    }

    private CompanyQueryData createQueryParametersForCompany(Company company, LocalDate start, LocalDate end) {
        CompanyQueryData ranges = createRanges(company, start, end);
        CompanyQueryData queryData = new CompanyQueryData();
        queryData.company = company;
        queryData.before = ranges.before;
        queryData.after = ranges.after;
        return queryData;
    }

    private CompanyQueryData createRanges(Company company, LocalDate start, LocalDate end) {
        LocalDate companyStart = getStartDate(company);
        LocalDate companyEnd = getEndDate(company);
        CompanyQueryData ranges = new CompanyQueryData();
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
