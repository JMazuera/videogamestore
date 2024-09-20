package co.com.devco.talentpool.videogamestore.domain.ports.in.customer;

import co.com.devco.talentpool.videogamestore.domain.models.Customer;
import java.util.Optional;

public interface IUpdateCustomerUseCase {
    Optional<Customer> updateCustomer(Long id, Customer updateCustomer);
}
