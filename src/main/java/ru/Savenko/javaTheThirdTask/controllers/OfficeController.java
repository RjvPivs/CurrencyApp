package ru.Savenko.javaTheThirdTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.Savenko.javaTheThirdTask.office.Office;
import ru.Savenko.javaTheThirdTask.repositories.OfficeRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/office")
public class OfficeController {

    @Autowired
    private OfficeRepository off;

    @GetMapping("")
    public List<Office> getOffice() {
        return off.findAll();
    }

    @GetMapping("/{id}")
    public Office getOfficeById(@PathVariable("id") UUID id) {
        return off.findById(id).orElse(null);
    }
}