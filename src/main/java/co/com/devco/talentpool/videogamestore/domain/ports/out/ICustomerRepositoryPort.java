package co.com.devco.talentpool.videogamestore.domain.ports.out;

import co.com.devco.talentpool.videogamestore.domain.models.Customer;
import java.util.List;
import java.util.Optional;

public interface ICustomerRepositoryPort {

    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    Optional<Customer> update(Long id, Customer customer);
    boolean deleteById(Long id);
}
