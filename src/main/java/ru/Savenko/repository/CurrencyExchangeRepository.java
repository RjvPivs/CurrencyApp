package ru.Savenko.repository;

import ru.Savenko.model.CurrencyExchange;

import java.sql.SQLException;
import java.util.List;

public interface CurrencyExchangeRepository {
    public CurrencyExchange findById(int id) throws SQLException;

    public List<CurrencyExchange> findAll();

    public List<CurrencyExchange> findByCode(String code);

    public int delete(int id);

    public void deleteAll();

    public int insert(CurrencyExchange curr);

    public int update(CurrencyExchange curr);
}
