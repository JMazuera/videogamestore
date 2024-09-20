package co.com.devco.talentpool.videogamestore.domain.ports.in.customer;

import co.com.devco.talentpool.videogamestore.domain.models.Customer;

public interface ICreateCustomerUseCase {
    Customer createCustomer(Customer customer);
}
