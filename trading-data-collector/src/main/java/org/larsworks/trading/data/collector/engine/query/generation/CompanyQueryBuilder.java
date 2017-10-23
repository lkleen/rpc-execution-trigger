package org.larsworks.trading.data.collector.engine.query.generation;

import org.larsworks.trading.data.collector.provider.nasdaq.companies.csv.Company;
import org.larsworks.trading.data.collector.provider.nasdaq.companies.csv.CompanyParser;
import org.larsworks.trading.data.collector.provider.yahoo.yql.YahooSelectQueryBuilder;

import javax.inject.Inject;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lars on 19.03.2017.
 */
public class CompanyQueryBuilder {

    @Inject
    PeriodicalSelectQueryParametersGenerator generator;

    @Inject
    CompanyParser companyParser;

    @Inject
    YahooSelectQueryBuilder queryBuilder;

    /**
     * @param company - the company to read the symbol from
     * @param queryRange - the overall range to query. it will be split to maxAllowedRange
     * @param maxAllowedPeriod - the maximum allowed period for a single query
     * @return a list of queries for a specific company, split to maximum allowed range
     */
    public List<Query> createQuerysForCompany(
            Company company,
            SelectQueryRange queryRange,
            Period maxAllowedPeriod) {
        List<SelectQueryParameters> queryParameters = generator.generateParameters(company.getSymbol(), queryRange, maxAllowedPeriod);
        List<Query> queries = new ArrayList<>();
        for (SelectQueryParameters parameters : queryParameters) {queries.add(queryBuilder.build(parameters));}
        return queries;
    }

}
