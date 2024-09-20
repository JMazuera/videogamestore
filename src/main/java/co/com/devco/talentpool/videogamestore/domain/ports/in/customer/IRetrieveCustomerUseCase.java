package co.com.devco.talentpool.videogamestore.domain.ports.in.customer;

import co.com.devco.talentpool.videogamestore.domain.models.Customer;
import java.util.List;
import java.util.Optional;

public interface IRetrieveCustomerUseCase {
    Optional<Customer> getCustomer(Long id);
    List<Customer> getAllCustomers();
}
