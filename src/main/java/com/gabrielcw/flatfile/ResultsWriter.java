package com.gabrielcw.flatfile;

import com.gabrielcw.domain.stats.SaleStatistics;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Singleton results file writer.
 */
public class ResultsWriter {

    private static ResultsWriter instance = new ResultsWriter();
    private ResultsWriter(){}
    public static ResultsWriter getInstance(){
        return instance;
    }


    public void writeOutFile(String path, String name, SaleStatistics stats) {
        PrintWriter writer = null;
        try {
            String fullPath = path + name.replace(".dat", ".done.dat");
            System.out.println(MessageFormat.format("Writing results file {0}", fullPath));
            writer = new PrintWriter(fullPath);
            String time= new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
            writer.println(MessageFormat.format("Results for {0} - {1}", fullPath, time));
            writer.println("-------------------------------------------------------------------------------------------");
            writer.println(MessageFormat.format("Amount of clients: {0}", stats.getCustomersAmount()));
            writer.println(MessageFormat.format("Amount of Salesman: {0}", stats.getSalesmenAmount()));
            writer.println(MessageFormat.format("Most expensive sale ID: {0}", stats.getMostExpensiveSaleId()));
            writer.println(MessageFormat.format("Worst salesman: {0}", stats.getWorstSalesmanName()));
            writer.println("-------------------------------------------------------------------------------------------");
            writer.println("");
            writer.println("");
            writer.println("");
            writer.println("NOTES:");
            writer.println("- The Worst salesman is the one which has the lowest sales total value.");
            writer.println("- Amount of clients and Salesmen considers only distinct ones.");
            writer.println("- If you change a file, the results are re-calculated and the old results are overwritten.");
            writer.println("- Date is UTC.");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
