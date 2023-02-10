package ru.Savenko.javaTheThirdTask.office;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "stuff")
public class Stuff {
    public Stuff(String surname, String name, String patronymic, Boolean sex, LocalDate birthDate, Double salaryMultiplier, Position position) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.sex = sex;
        this.birthDate = birthDate;
        this.salaryMultiplier = salaryMultiplier;
        this.position = position;
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "salary_multiplier")
    @JsonProperty("salary_multiplier")
    private Double salaryMultiplier;

    @ManyToOne(targetEntity = Position.class, optional = false)
    @JoinColumn(name = "position_id")
    private Position position;

    public Stuff() {

    }

    @Override
    public String toString() {
        return surname + ' ' + name + ' ' + patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public double getSalaryMultiplier() {
        return salaryMultiplier;
    }

    public void setSalaryMultiplier(double salaryMultiplier) {
        this.salaryMultiplier = salaryMultiplier;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

