package ru.Savenko.javaTheThirdTask;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.Savenko.javaTheThirdTask.repositories.ClientRepository;
import ru.Savenko.javaTheThirdTask.repositories.OfficeRepository;
import ru.Savenko.javaTheThirdTask.repositories.PositionRepository;
import ru.Savenko.javaTheThirdTask.repositories.StuffRepository;
import ru.Savenko.javaTheThirdTask.utils.FillTheRepos;

@SpringBootApplication
public class JavaTheThirdTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaTheThirdTaskApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(ClientRepository res, PositionRepository pos, OfficeRepository off, StuffRepository st){
		return args -> {
			FillTheRepos fillTheRepos = new FillTheRepos(res, pos, off, st);
			fillTheRepos.fill();
		};
	}

}
