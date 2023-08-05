package by.semashko.greenjokerquestbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GreenjokerquestbotApplication {

    public static void main(String[] args) {

        SpringApplication.run(GreenjokerquestbotApplication.class, args);
    }

}
