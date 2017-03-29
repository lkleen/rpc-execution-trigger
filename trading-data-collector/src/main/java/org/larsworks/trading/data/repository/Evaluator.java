package org.larsworks.trading.data.repository;

import org.larsworks.trading.data.collector.engine.query.generation.SelectQueryParameters;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LKLeen on 29.03.2017.
 */
public class Evaluator {
    public List<SelectQueryParameters> getMissingEntries(
            Repository repository,
            LocalDate start,
            LocalDate end,
            String... symbols
    ) {
        List<SelectQueryParameters> result = new ArrayList<>();
        return null;
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
