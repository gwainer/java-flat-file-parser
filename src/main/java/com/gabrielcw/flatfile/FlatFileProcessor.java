package com.gabrielcw.flatfile;


import com.gabrielcw.domain.Customer;
import com.gabrielcw.domain.Sale;
import com.gabrielcw.domain.Salesman;
import com.gabrielcw.domain.stats.SaleStatistics;
import com.github.ffpojo.FFPojoHelper;
import com.github.ffpojo.exception.FFPojoException;

import java.io.*;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class FlatFileProcessor {

    private SaleStatistics statistics;

    private static FlatFileProcessor instance = new FlatFileProcessor();

    private FlatFileProcessor(){}

    public static FlatFileProcessor getInstance(){
        return instance;
    }


    private void readFile(String inPath) throws IOException, FFPojoException {
        FFPojoHelper ffpojo = FFPojoHelper.getInstance();
        BufferedReader br = new BufferedReader(new FileReader(inPath));
        String line;
        while ( (line = br.readLine()) != null) {
            if (isSalesman(line)) {
                processSalesman(line, ffpojo);
            } else if (isCustomer(line)) {
                processCustomer(line, ffpojo);
            } else if (isSale(line)) {
                processSale(line, ffpojo);
            }
        }
        br.close();
    }

    private boolean isMostExpensiveSale(Sale sale) {
        return sale.getSaleTotalPrice() > getStatistics().getMostExpensiveSaleValue();
    }

    private void processSale(String line, FFPojoHelper ffpojo) throws FFPojoException {
        Sale sale = ffpojo.createFromText(Sale.class, line);
        if (isMostExpensiveSale(sale)) {
            getStatistics().setMostExpensiveSaleValue(sale.getSaleTotalPrice());
            getStatistics().setMostExpensiveSaleId(sale.getSaleId());
        }
        getStatistics().addToSalesmanTotals(sale.getSalesmanName(), sale.getSaleTotalPrice());
    }

    private void processSalesman(String line, FFPojoHelper ffpojo) throws FFPojoException {
        Salesman salesman = ffpojo.createFromText(Salesman.class, line);
        getStatistics().addSalesmanCpf(salesman.getCpf());
    }

    private void processCustomer(String line, FFPojoHelper ffpojo) throws FFPojoException {
        Customer customer = ffpojo.createFromText(Customer.class, line);
        getStatistics().addCustomerCnpj(customer.getCnpj());
    }

    private boolean isSalesman(String line) {
        return line.startsWith("001");
    }

    private boolean isCustomer(String line) {
        return line.startsWith("002");
    }

    private boolean isSale(String line) {
        return line.startsWith("003");
    }

    public SaleStatistics getStatistics() {
        if (statistics == null) {
            statistics = new SaleStatistics();
        }
        return statistics;
    }

    public void resetStatistics() {
        statistics = null;
    }

    public void processEvent(Path name, Path child) {
        try {
            System.out.format("Will process file %s\n", child);
            resetStatistics();
            readFile(child.toString());
            ResultsWriter.getInstance().writeOutFile(System.getenv("HOMEPATH") + "/data/out/", name.toString(),
                    getStatistics());
        } catch (IOException | FFPojoException e) {
            System.out.println(MessageFormat.format("Error reading file {0}", child));
            e.printStackTrace();
        }
    }

    public void processFile(File file) {
        if (!file.getName().endsWith(".dat")) {
            System.out.println(MessageFormat.format("Skipping non-dat file {0}", file.getAbsolutePath()));
        } else {
            resetStatistics();
            try {
                readFile(file.getAbsolutePath());
                ResultsWriter.getInstance().writeOutFile(System.getenv("HOMEPATH") + "/data/out/", file.getName(),
                        getStatistics());
            } catch (IOException | FFPojoException e) {
                System.out.println(MessageFormat.format("Error reading file {0}", file.getAbsolutePath()));
                e.printStackTrace();
            }
        }
    }
}
