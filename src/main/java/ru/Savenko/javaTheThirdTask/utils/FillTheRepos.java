package ru.Savenko.javaTheThirdTask.utils;

import ru.Savenko.javaTheThirdTask.office.Client;
import ru.Savenko.javaTheThirdTask.office.Office;
import ru.Savenko.javaTheThirdTask.office.Position;
import ru.Savenko.javaTheThirdTask.office.Stuff;
import ru.Savenko.javaTheThirdTask.repositories.ClientRepository;
import ru.Savenko.javaTheThirdTask.repositories.OfficeRepository;
import ru.Savenko.javaTheThirdTask.repositories.PositionRepository;
import ru.Savenko.javaTheThirdTask.repositories.StuffRepository;

import java.time.LocalDate;

public class FillTheRepos {

    private ClientRepository client;
    private PositionRepository position;
    private OfficeRepository office;
    private StuffRepository stuff;

    public FillTheRepos(ClientRepository client, PositionRepository position, OfficeRepository office, StuffRepository stuff) {
        this.position = position;
        this.client = client;
        this.office = office;
        this.stuff = stuff;
    }

    public void fill() {
        fillClients();
        fillPositions();
        fillOffice();
        fillStuff();
    }

    private void fillPositions() {
        Position p1 = new Position("President", 10000000);
        Position p2 = new Position("Consultant", 40000);
        position.save(p1);
        position.save(p2);
    }

    private void fillStuff() {
        var a = position.findAll();
        Stuff st = new Stuff("Scholz", "Olaf", "Gerhard", false, LocalDate.now(), 2.0, a.get(0));
        Stuff st1 = new Stuff("Lukashenko", "Alexander", "Grigorievich", false, LocalDate.now(), 2.0, a.get(1));
        stuff.save(st);
        stuff.save(st1);
    }

    private void fillClients() {
        Client cl = new Client("Putin", "Vladimir", "Vladimirovich", "1", "1", "88005553535", false, LocalDate.now());
        Client cl2 = new Client("Biden", "Joe", "Joe", "1", "1", "88005553535", false, LocalDate.now());
        Client cl3 = new Client("Macron", "Emmanuel", "Jean-Michel", "1", "1", "88005553535", false, LocalDate.now());
        client.save(cl);
        client.save(cl2);
        client.save(cl3);
    }

    private void fillOffice() {
        Office of = new Office("Moscow", "Kremlin", 100);
        Office of1 = new Office("Washington", "White house", 99);
        office.save(of);
        office.save(of1);
    }
}
