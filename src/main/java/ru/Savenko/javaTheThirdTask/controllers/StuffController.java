package ru.Savenko.javaTheThirdTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.Savenko.javaTheThirdTask.office.Stuff;
import ru.Savenko.javaTheThirdTask.repositories.StuffRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/stuff")
public class StuffController {

    @Autowired
    private StuffRepository stuff;

    @GetMapping("")
    public List<Stuff> getStuff() {
        return stuff.findAll();
    }

    @GetMapping("/{id}")
    public Stuff getStuffById(@PathVariable("id") UUID id) {
        return stuff.findById(id).orElse(null);
    }
}
