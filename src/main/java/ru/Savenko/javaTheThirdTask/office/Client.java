package ru.Savenko.javaTheThirdTask.office;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "sex")
    private Boolean sex;

    @JsonProperty("birth_date")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "inn")
    private String inn;

    @JsonProperty("passport_serial")
    @Column(name = "passport_serial")
    private String passportSerial;

    @Column(name = "phone")
    private String phone;

    public Client() {

    }

    public Client(String surname, String name, String patronymic, String inn, String passportSerial, String phone, Boolean sex, LocalDate birthDate) {
        this.surname = surname;
        id = UUID.randomUUID();
        this.name = name;
        this.patronymic = patronymic;
        this.inn = inn;
        this.passportSerial = passportSerial;
        this.phone = phone;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return surname + ' ' + name + ' ' + patronymic;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getPassportSerial() {
        return passportSerial;
    }

    public void setPassportSerial(String passportSerial) {
        this.passportSerial = passportSerial;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setBirthDate(LocalDate date) {
        birthDate = date;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

}

