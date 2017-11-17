package za.connect.cashdispense.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import za.connect.cashdispense.persistance.repository.UserRepository;
import za.connect.cashdispense.services.CashDispenseService;

@Configuration
public class ApplicationConfigurations {

    @Bean
    public CashDispenseService getCoinDispenseService() {
        return new CashDispenseService();
    }

    @Autowired
    private UserRepository userRepository;

}
