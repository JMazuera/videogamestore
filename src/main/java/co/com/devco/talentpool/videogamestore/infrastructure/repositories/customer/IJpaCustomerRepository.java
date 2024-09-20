package co.com.devco.talentpool.videogamestore.infrastructure.repositories.customer;

import co.com.devco.talentpool.videogamestore.infrastructure.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJpaCustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
