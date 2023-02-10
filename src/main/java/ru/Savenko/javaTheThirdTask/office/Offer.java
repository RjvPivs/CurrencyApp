package ru.Savenko.javaTheThirdTask.office;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @JsonProperty("serial_number")
    @Column(name = "serial_number")
    private String serialNumber;

    @JsonProperty("sign_date")
    @Column(name = "sign_date")
    private LocalDate signDate;

    @JsonProperty("ending_date")
    @Column(name = "ending_date")
    private LocalDate endingDate;

    @JsonProperty("start_date")
    @Column(name = "start_date")
    private LocalDate startDate;

    @ManyToOne(targetEntity = Client.class)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(targetEntity = Office.class, optional = false)
    @JoinColumn(name = "office_id")
    private Office office;

    @ManyToOne(targetEntity = Stuff.class)
    @JoinColumn(name = "stuff_id")
    private Stuff stuff;


    public Offer(LocalDate startDate, LocalDate endingDate, Client client, Stuff stuff) {
        this.startDate = startDate;
        this.endingDate = endingDate;
        this.client = client;
        this.stuff = stuff;
        id = UUID.randomUUID();
    }

    public Offer() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDate getSignDate() {
        return signDate;
    }

    public void setSignDate(LocalDate signDate) {
        this.signDate = signDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endDate) {
        this.endingDate = endDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Stuff getStuff() {
        return stuff;
    }

    public void setStuff(Stuff stuff) {
        this.stuff = stuff;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
