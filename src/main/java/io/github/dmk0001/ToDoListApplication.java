package io.github.dmk0001;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ToDoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoListApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(TaskRepository taskRepository){
        return args -> {
            taskRepository.save(new Task("Kup mleko", "Pamiętaj, aby kupić mleko w drodze do domu"));
            taskRepository.save(new Task("Trening", "30 minut biegania w parku"));
            taskRepository.save(new Task("Spotkanie z Janem", "Umówione spotkanie o 15:00 w kawiarni"));
        };
    }
}