package com.gabrielcw.domain.stats;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SaleStatistics {

    private Set<String> customerCnpjs;
    private Set<String> salesmanCpfs;
    private long mostExpensiveSaleId;
    private float mostExpensiveSaleValue;
    private String worstSalesmanName;
    private Map<String, Float> salesmanTotals;

    public SaleStatistics() {
        this.customerCnpjs = new HashSet<>();
        this.salesmanCpfs = new HashSet<>();
    }

    public long getCustomersAmount() {
        return customerCnpjs.size();
    }

    public long getSalesmenAmount() {
        return salesmanCpfs.size();
    }

    public long getMostExpensiveSaleId() {
        return mostExpensiveSaleId;
    }

    public void setMostExpensiveSaleId(long mostExpensiveSaleId) {
        this.mostExpensiveSaleId = mostExpensiveSaleId;
    }


    public float getMostExpensiveSaleValue() {
        return mostExpensiveSaleValue;
    }

    public void setMostExpensiveSaleValue(float mostExpensiveSaleValue) {
        this.mostExpensiveSaleValue = mostExpensiveSaleValue;
    }

    public Map<String, Float> getSalesmanTotals() {
        if (salesmanTotals == null) {
            salesmanTotals = new HashMap<>();
        }
        return salesmanTotals;
    }


    public void addToSalesmanTotals(String salesmanName, Float saleValue) {

        if (getSalesmanTotals().containsKey(salesmanName)) {
            Float currentValue = getSalesmanTotals().get(salesmanName);
            Float newValue = currentValue + saleValue;
            getSalesmanTotals().put(salesmanName, newValue);
        } else {
            getSalesmanTotals().put(salesmanName, saleValue);
        }
        System.out.println(MessageFormat.format("Salesman Totals for {0} is now {1}", salesmanName,
                getSalesmanTotals().get(salesmanName)));
    }

    public String getWorstSalesmanName() {
        if (worstSalesmanName == null) {
            Float lowestValue = Float.MAX_VALUE;
            for (Map.Entry<String, Float> entry : salesmanTotals.entrySet()) {
                if (entry.getValue() < lowestValue) {
                    lowestValue = entry.getValue();
                    worstSalesmanName = entry.getKey();
                }
            }
        }
        return worstSalesmanName;
    }

    public void addSalesmanCpf(String cpf) {
        salesmanCpfs.add(cpf);
    }

    public void addCustomerCnpj(String cnpj) {
        customerCnpjs.add(cnpj);
    }
}

