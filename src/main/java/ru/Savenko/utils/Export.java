package ru.Savenko.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import ru.Savenko.model.CurrencyExchange;
import ru.Savenko.repository.CurrencyExchangeRepositorySqliteImpl;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Export {
    public static void ExportCSV(String selectedFile) {
        try {
            StringWriter writer = new StringWriter();
            List<CurrencyExchange> allCurrencies = CurrencyExchangeRepositorySqliteImpl.getInstance().findAll();
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            printer.printRecord("id", "value", "nominal", "name", "code", "date");
            for (CurrencyExchange cur : allCurrencies) {
                printer.printRecord(
                        cur.getId(),
                        cur.getValue(),
                        cur.getNominal(),
                        cur.getCurrencyName(),
                        cur.getCurrencyCode(),
                        cur.getDate()
                );
            }
            OutputStreamWriter wr = new OutputStreamWriter(new FileOutputStream(selectedFile, false), "UTF-8");
            wr.write(new String(writer.toString().replaceAll(",", ";").getBytes(StandardCharsets.UTF_8), "UTF-8"));
            wr.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static void ExportJSON(String selectedFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            FileWriter writer = new FileWriter(selectedFile);
            List<CurrencyExchange> allCurrencies = CurrencyExchangeRepositorySqliteImpl.getInstance().findAll();
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(allCurrencies);
            writer.write(s);
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

