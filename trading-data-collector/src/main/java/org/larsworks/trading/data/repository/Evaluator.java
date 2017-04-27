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
            //SelectQueryParameters parameters = createParametersFor(repository, symbol, start, end);
        }
        return null;
    }

    private List<SelectQueryParameters> createParametersFor(Repository repository, String symbol, LocalDate start, LocalDate end) {
        Company company = getCompany(repository, symbol);
        if(company != null) {
            SelectQueryRanges range = createRanges (company, start, end);
            return null;
        } else {
            
        }
        return null;
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
        } catch (ArrayIndexOutOfBoundsException e) {
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
