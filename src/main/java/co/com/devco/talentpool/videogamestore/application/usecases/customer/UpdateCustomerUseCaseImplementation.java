package co.com.devco.talentpool.videogamestore.application.usecases.customer;

import co.com.devco.talentpool.videogamestore.domain.models.Customer;
import co.com.devco.talentpool.videogamestore.domain.ports.in.customer.IUpdateCustomerUseCase;
import co.com.devco.talentpool.videogamestore.domain.ports.out.ICustomerRepositoryPort;

import java.util.Optional;

public class UpdateCustomerUseCaseImplementation implements IUpdateCustomerUseCase {

    private final ICustomerRepositoryPort iCustomerRepositoryPort;

    public UpdateCustomerUseCaseImplementation(ICustomerRepositoryPort iCustomerRepositoryPort) {
        this.iCustomerRepositoryPort = iCustomerRepositoryPort;
    }

    @Override
    public Optional<Customer> updateCustomer(Long id, Customer updateCustomer) {
        return iCustomerRepositoryPort.update(id, updateCustomer);
    }
}
