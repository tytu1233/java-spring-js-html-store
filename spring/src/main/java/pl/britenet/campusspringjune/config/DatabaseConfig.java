package pl.britenet.campusspringjune.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.britenet.consoleapp.service.DatabaseService;

import java.net.ConnectException;

@Configuration
public class DatabaseConfig {
    @Bean
    public DatabaseService getDatabaseService() throws ConnectException {
        return new DatabaseService();
    }

}
