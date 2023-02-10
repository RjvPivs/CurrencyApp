package ru.Savenko.cbr;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import retrofit.RestAdapter;
import ru.Savenko.DTO.ValCurs;
import ru.Savenko.DTO.Valute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.Savenko.model.CurrencyExchange;
import ru.Savenko.repository.CurrencyExchangeRepositorySqliteImpl;
import ru.Savenko.utils.DateConverter;
import ru.Savenko.utils.JacksonConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ValuteToCurrencyExchangeConverter {
    private static final Logger log = LoggerFactory.getLogger(ValuteToCurrencyExchangeConverter.class);

    public static void update() {
        try {
            log.info("Preparation for request.");
            String url = "https://www.cbr.ru/";
            RestAdapter retrofit = new RestAdapter.Builder()
                    .setEndpoint(url).setConverter(new JacksonConverter(new XmlMapper())).build();
            Cbr cbr = retrofit.create(Cbr.class);
            LocalDate date = LocalDate.now();
            StringBuilder str = new StringBuilder();
            if (date.getDayOfMonth() < 10){
                str.append(0).append(date.getDayOfMonth());
            } else {
                str.append(date.getDayOfMonth());
            }
            str.append('/');
            if (date.getMonth().getValue() < 10){
                str.append(0).append(date.getMonth().getValue());
            } else {
                str.append(date.getMonth().getValue());
            }
            str.append('/').append(date.getYear());
            ValCurs exchange = cbr.getExchange(str.toString());
            log.info("Successful request.");
            List<CurrencyExchange> curr = new ArrayList<>();
            for (Valute v : exchange.getValuteList()) {
                curr.add(new CurrencyExchange(0, v.getValue(), v.getNominal(), v.getName(), v.getCharCode(), DateConverter.convertToLocalDateViaInstant(exchange.getDate())));
            }
            CurrencyExchangeRepositorySqliteImpl.getInstance().deleteAll();
            for (CurrencyExchange c : curr) {
                CurrencyExchangeRepositorySqliteImpl.getInstance().insert(c);
            }
            log.info("Successful insert.");
        } catch (Exception e) {
            log.error("Requesting exception.");
            throw new RuntimeException();
        }
    }
}
