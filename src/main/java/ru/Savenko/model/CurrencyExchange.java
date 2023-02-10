package ru.Savenko.model;

import java.time.LocalDate;

public class CurrencyExchange {
    private int id;
    private double value;
    private int nominal;
    private String currencyName;
    private String currencyCode;
    private LocalDate date;

    public CurrencyExchange() {
    }

    public CurrencyExchange(int id, double value, int nominal, String currencyName, String currencyCode, LocalDate date) {
        this.id = id;
        this.value = value;
        this.nominal = nominal;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public int getNominal() {
        return nominal;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public LocalDate getDate() {
        return date;
    }
}

