package ru.Savenko.javaTheThirdTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.Savenko.javaTheThirdTask.dto.NewOfferParameters;
import ru.Savenko.javaTheThirdTask.dto.OfferStatistic;
import ru.Savenko.javaTheThirdTask.office.LeasingOfferService;
import ru.Savenko.javaTheThirdTask.office.Offer;
import ru.Savenko.javaTheThirdTask.repositories.ClientRepository;
import ru.Savenko.javaTheThirdTask.repositories.OfferRepository;
import ru.Savenko.javaTheThirdTask.repositories.OfficeRepository;
import ru.Savenko.javaTheThirdTask.repositories.StuffRepository;
import ru.Savenko.javaTheThirdTask.utils.Search;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/offer")
public class OfferController {

    @Autowired
    private OfferRepository offer;

    @Autowired
    private StuffRepository stuff;

    @Autowired
    private ClientRepository client;

    @Autowired
    private OfficeRepository office;

    @GetMapping("")
    public List<Offer> getOffer() {
        return offer.findAll();
    }

    @GetMapping("/{id}")
    public Offer getOfferById(@PathVariable("id") UUID id) {
        return offer.findById(id).orElse(null);
    }

    @PutMapping("")
    public Offer signNewOffer(@RequestBody NewOfferParameters offerParameters) {
        Objects.requireNonNull(offerParameters);
        Objects.requireNonNull(offerParameters.getClientId());
        Objects.requireNonNull(offerParameters.getStuffId());
        Objects.requireNonNull(offerParameters.getStartDate());
        Objects.requireNonNull(offerParameters.getEndingDate());
        Offer off = LeasingOfferService.getInstance().signNewOffer(offerParameters.getStartDate(), offerParameters.getEndingDate(), client.findById(offerParameters.getClientId()).orElse(null), stuff.findById(offerParameters.getStuffId()).orElse(null));
        off.setOffice(office.findAll().get(0));
        off.setSerialNumber("123");
        off.setSignDate(LocalDate.now());
        Search.addOff(off);
        offer.save(off);
        return off;
    }

    @GetMapping("/statistic")
    public OfferStatistic getOfferStatistic() {
        OfferStatistic offStat = new OfferStatistic(Search.getStuff(), Search.getClient(), Search.sur);
        return offStat;
    }
}
