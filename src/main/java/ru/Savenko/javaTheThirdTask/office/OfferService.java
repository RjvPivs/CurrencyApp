package ru.Savenko.javaTheThirdTask.office;

import java.time.LocalDate;

public interface OfferService {
    public default Offer signNewOffer(LocalDate startDate, LocalDate endDate, Client client, Stuff stuff) {
        return new Offer(startDate, endDate, client, stuff);
    }
}
