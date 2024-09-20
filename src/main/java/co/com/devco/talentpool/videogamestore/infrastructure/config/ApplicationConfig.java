package co.com.devco.talentpool.videogamestore.infrastructure.config;

import co.com.devco.talentpool.videogamestore.application.services.CustomerService;
import co.com.devco.talentpool.videogamestore.application.services.VideoGameService;
import co.com.devco.talentpool.videogamestore.application.usecases.customer.CreateCustomerUseCaseImplementation;
import co.com.devco.talentpool.videogamestore.application.usecases.customer.DeleteCustomerUseCaseImplementation;
import co.com.devco.talentpool.videogamestore.application.usecases.customer.RetrieveCustomerUseCaseImplementation;
import co.com.devco.talentpool.videogamestore.application.usecases.customer.UpdateCustomerUseCaseImplementation;
import co.com.devco.talentpool.videogamestore.application.usecases.videogame.*;
import co.com.devco.talentpool.videogamestore.domain.ports.out.ICustomerRepositoryPort;
import co.com.devco.talentpool.videogamestore.domain.ports.out.IVideogameRepositoryPort;
import co.com.devco.talentpool.videogamestore.infrastructure.repositories.customer.JpaCustomerRepositoryAdapter;
import co.com.devco.talentpool.videogamestore.infrastructure.repositories.videogame.JpaVideoGameRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public VideoGameService videoGameService(IVideogameRepositoryPort iVideogameRepositoryPort){
        return new VideoGameService(
                new CreateVideoGameUseCaseImplementation(iVideogameRepositoryPort),
                new DeleteVideoGameUseCaseImplementation(iVideogameRepositoryPort),
                new RetrieveVideoGameUseCaseImplementation(iVideogameRepositoryPort),
                new UpdateVideogameUseCaseImplementation(iVideogameRepositoryPort),
                new PurchaseVideoGameUseCaseImplementation(iVideogameRepositoryPort)
        );
    }

    @Bean
    public IVideogameRepositoryPort iVideogameRepositoryPort(JpaVideoGameRepositoryAdapter jpaVideoGameRepositoryAdapter){
        return jpaVideoGameRepositoryAdapter;
    }

    @Bean
    public CustomerService customerService(ICustomerRepositoryPort iCustomerRepositoryPort){
        return new CustomerService(
                new CreateCustomerUseCaseImplementation(iCustomerRepositoryPort),
                new DeleteCustomerUseCaseImplementation(iCustomerRepositoryPort),
                new RetrieveCustomerUseCaseImplementation(iCustomerRepositoryPort),
                new UpdateCustomerUseCaseImplementation(iCustomerRepositoryPort)
        );
    }

    @Bean
    public ICustomerRepositoryPort iCustomerRepositoryPort(JpaCustomerRepositoryAdapter jpaCustomerRepositoryAdapter){
        return jpaCustomerRepositoryAdapter;
    }
}