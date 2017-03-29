package org.larsworks.trading.data.repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LKLeen on 29.03.2017.
 */
public class Repository {
    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    List<Company> companies = new ArrayList<>();
}
