package ru.Savenko.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.Savenko.database.Database;
import ru.Savenko.model.CurrencyExchange;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class CurrencyExchangeRepositorySqliteImpl implements CurrencyExchangeRepository {
    private static final Logger log = LoggerFactory.getLogger(CurrencyExchangeRepositorySqliteImpl.class);
    private static CurrencyExchangeRepository instance;
    private static Connection con;

    private CurrencyExchangeRepositorySqliteImpl() {
        con = Database.getInstance().getConnection();
    }

    public static CurrencyExchangeRepository getInstance() {
        if (instance == null) {
            log.debug("SQL implementation class created.");
            instance = new CurrencyExchangeRepositorySqliteImpl();
        }
        log.debug("SQL implementation class returned.");
        return instance;
    }

    @Override
    public CurrencyExchange findById(int id) {
        log.info("ID search started.");
        CurrencyExchange curr = null;
        try {
            PreparedStatement st = con.prepareStatement("SELECT id, value, nominal, currency_name, currency_code, date FROM currency WHERE id=?");
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                curr = new CurrencyExchange(resultSet.getInt(1), resultSet.getDouble(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6).toLocalDate());
            }
        } catch (Exception e) {
            log.error("ID search failed.");
            throw new RuntimeException();
        }
        log.info("ID search ended.");
        return curr;
    }

    @Override
    public List<CurrencyExchange> findAll() {
        log.info("Total search started.");
        List<CurrencyExchange> curr = new ArrayList<>();
        try {
            PreparedStatement st = con.prepareStatement("SELECT id, value, nominal, currency_name, currency_code, date FROM currency");
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                curr.add(new CurrencyExchange(resultSet.getInt(1), resultSet.getDouble(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6).toLocalDate()));
            }
        } catch (Exception e) {
            log.error("Total search failed.");
            throw new RuntimeException();
        }
        log.info("Total search ended.");
        return curr;
    }

    @Override
    public List<CurrencyExchange> findByCode(String code) {
        log.info("Code search started.");
        List<CurrencyExchange> curr = new ArrayList<>();
        try {
            PreparedStatement st = con.prepareStatement("SELECT id, value, nominal, currency_name, currency_code, date FROM currency WHERE currency_code=?");
            st.setString(1, code);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                curr.add(new CurrencyExchange(resultSet.getInt(1), resultSet.getDouble(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6).toLocalDate()));
            }
        } catch (Exception e) {
            log.error("Code search failed.");
            throw new RuntimeException();
        }
        log.info("Code search ended.");
        return curr;
    }

    @Override
    public int delete(int id) {
        log.info("ID delete started.");
        int del = 0;
        try {
            PreparedStatement st = con.prepareStatement("DELETE FROM currency WHERE id=?");
            st.setInt(1, id);
            del = st.executeUpdate();
        } catch (Exception e) {
            log.error("ID delete failed.");
            throw new RuntimeException();
        }
        log.info("ID delete ended.");
        return del;
    }

    @Override
    public void deleteAll() {
        log.info("Total delete started.");
        try {
            PreparedStatement st = con.prepareStatement("DELETE FROM currency");
            st.executeUpdate();
        } catch (Exception e) {
            log.error("Total delete failed.");
            throw new RuntimeException();
        }
        log.info("Total delete ended.");
    }

    @Override
    public int insert(CurrencyExchange curr) {
        log.info("Total insert started.");
        int ins = 0;
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO currency (value,nominal,currency_name,currency_code, date) VALUES (?,?,?,?,?)");
            st.setDouble(1, curr.getValue());
            st.setInt(2, curr.getNominal());
            st.setString(3, curr.getCurrencyName());
            st.setString(4, curr.getCurrencyCode());
            st.setDate(5, new Date(curr.getDate().toEpochSecond(LocalTime.of(0, 0, 0), ZoneOffset.UTC) * 1000));
            ins = st.executeUpdate();
        } catch (Exception e) {
            log.error("Total insert failed.");
            throw new RuntimeException();
        }
        log.info("Total insert ended.");
        return ins;
    }

    @Override
    public int update(CurrencyExchange curr) {
        log.info("Currency update started.");
        int upd = 0;
        try {
            PreparedStatement st = con.prepareStatement("UPDATE currency SET value=?,nominal=?,currency_name=?,currency_code=?, date=? WHERE id=?");
            st.setInt(2, curr.getNominal());
            st.setDouble(1, curr.getValue());
            st.setString(3, curr.getCurrencyName());
            st.setString(4, curr.getCurrencyCode());
            st.setDate(5, new Date(curr.getDate().getYear(), curr.getDate().getMonthValue(), curr.getDate().getDayOfMonth()));
            st.setInt(6, curr.getId());
            upd = st.executeUpdate();
        } catch (Exception e) {
            log.error("Currency update failed.");
            throw new RuntimeException();
        }
        log.info("Currency update ended.");
        return upd;
    }
}
