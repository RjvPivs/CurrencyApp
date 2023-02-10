package ru.Savenko.javaTheThirdTask.office;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "office")
public class Office {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "address")
    private String address;

    @JsonProperty("law_address")
    @Column(name = "law_address")
    private String lawAddress;

    @JsonProperty("cabinets_count")
    @Column(name = "cabinets_count")
    private Integer cabinetsCount;

    public Office(String address, String lawAddress, Integer cabinetsCount) {
        this.address = address;
        this.lawAddress = lawAddress;
        this.cabinetsCount = cabinetsCount;
        id = UUID.randomUUID();
    }

    public Office() {
    }

    public UUID getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLawAddress() {
        return lawAddress;
    }

    public void setLawAddress(String lawAddress) {
        this.lawAddress = lawAddress;
    }

    public Integer getCabinetsCount() {
        return cabinetsCount;
    }

    public void setCabinetsCount(Integer cabinetCount) {
        this.cabinetsCount = cabinetCount;
    }
}
