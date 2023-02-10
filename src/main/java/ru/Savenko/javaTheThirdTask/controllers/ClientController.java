package ru.Savenko.javaTheThirdTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.Savenko.javaTheThirdTask.office.Client;
import ru.Savenko.javaTheThirdTask.repositories.ClientRepository;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    private ClientRepository client;

    @GetMapping("")
    public List<Client> getClients() {
        return client.findAll();
    }

    @GetMapping("/{id}")
    public Client getExactClient(@PathVariable("id") UUID id) {
        return client.findById(id).orElse(null);
    }
}
