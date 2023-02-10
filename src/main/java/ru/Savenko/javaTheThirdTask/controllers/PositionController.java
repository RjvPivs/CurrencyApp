package ru.Savenko.javaTheThirdTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.Savenko.javaTheThirdTask.office.Position;
import ru.Savenko.javaTheThirdTask.repositories.PositionRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/position")
public class PositionController {

    @Autowired
    private PositionRepository position;

    @GetMapping("")
    public List<Position> getPositions() {
        return position.findAll();
    }

    @GetMapping("/{id}")
    public Position getPosition(@PathVariable("id") UUID id) {
        return position.findById(id).orElse(null);
    }
}
