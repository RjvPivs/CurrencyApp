package ru.Savenko.javaTheThirdTask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.Savenko.javaTheThirdTask.office.Stuff;

import java.util.UUID;

@Repository
public interface StuffRepository extends JpaRepository<Stuff, UUID> {
}