package co.com.devco.talentpool.videogamestore.application.usecases.customer;

import co.com.devco.talentpool.videogamestore.domain.models.Customer;
import co.com.devco.talentpool.videogamestore.domain.ports.in.customer.IRetrieveCustomerUseCase;
import co.com.devco.talentpool.videogamestore.domain.ports.out.ICustomerRepositoryPort;

import java.util.List;
import java.util.Optional;

public class RetrieveCustomerUseCaseImplementation implements IRetrieveCustomerUseCase {

    private final ICustomerRepositoryPort iCustomerRepositoryPort;

    public RetrieveCustomerUseCaseImplementation(ICustomerRepositoryPort iCustomerRepositoryPort) {
        this.iCustomerRepositoryPort = iCustomerRepositoryPort;
    }

    @Override
    public Optional<Customer> getCustomer(Long id) {
        return iCustomerRepositoryPort.findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return iCustomerRepositoryPort.findAll();
    }
}
