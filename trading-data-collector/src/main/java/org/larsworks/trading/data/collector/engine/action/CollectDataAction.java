package org.larsworks.trading.data.collector.engine.action;

import de.steinberg.engine.core.engine.action.AbstractAction;
import lombok.extern.slf4j.Slf4j;
import org.larsworks.trading.data.collector.finance.nasdaq.companies.csv.Company;
import org.larsworks.trading.data.collector.finance.nasdaq.companies.csv.CompanyParser;
import org.larsworks.trading.data.collector.repository.Repository;

import javax.inject.Inject;
import java.io.InputStream;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Lars Kleen
 * @since ?version
 *        Date: 08.01.17
 *        Time: 17:44
 */
@Slf4j
public class CollectDataAction extends AbstractAction {

    boolean initialized = false;

    @Inject
    Repository repository;

    @Inject
    CompanyParser companyParser;


    @Override
    public void execute() {
        if (!initialized) {
            initialize();
        }
        collectData();

    }

    private void initialize() {
        initializeCompanies();
        initializePeriod();
        initialized = true;
    }

    private void initializePeriod() {
        log.info("start date: ");
        log.info("end date: ");
    }

    private void initializeCompanies() {
        InputStream input = getClass().getClassLoader().getSystemResourceAsStream("nasdaq-companylist.csv");
        Set<Company> companies = new TreeSet<>(companyParser.read(Company.class, input));
        log.info("loaded " + companies.size() + " symbols");
    }

    private void collectData() {
        log.info("triggered data collection");
    }
}
