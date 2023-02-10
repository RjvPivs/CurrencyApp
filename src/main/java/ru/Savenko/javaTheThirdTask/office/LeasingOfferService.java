package ru.Savenko.javaTheThirdTask.office;

import java.time.LocalDate;

public class LeasingOfferService implements OfferService {
    private static LeasingOfferService leasingOfferService;

    public LeasingOfferService() {
    }

    public static LeasingOfferService getInstance() {
        if (leasingOfferService == null) {
            leasingOfferService = new LeasingOfferService();
        }
        return leasingOfferService;
    }

    public Offer signNewOffer(LocalDate startDate, LocalDate endDate, Client client, Stuff stuff) {
        return new Offer(startDate, endDate, client, stuff);
    }
}
