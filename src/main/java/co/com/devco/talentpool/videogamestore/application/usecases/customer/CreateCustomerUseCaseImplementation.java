package co.com.devco.talentpool.videogamestore.application.usecases.customer;

import co.com.devco.talentpool.videogamestore.domain.models.Customer;
import co.com.devco.talentpool.videogamestore.domain.ports.in.customer.ICreateCustomerUseCase;
import co.com.devco.talentpool.videogamestore.domain.ports.out.ICustomerRepositoryPort;

public class CreateCustomerUseCaseImplementation implements ICreateCustomerUseCase {

    private final ICustomerRepositoryPort iCustomerRepositoryPort;

    public CreateCustomerUseCaseImplementation(ICustomerRepositoryPort iCustomerRepositoryPort) {
        this.iCustomerRepositoryPort = iCustomerRepositoryPort;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return iCustomerRepositoryPort.save(customer);
    }
}
