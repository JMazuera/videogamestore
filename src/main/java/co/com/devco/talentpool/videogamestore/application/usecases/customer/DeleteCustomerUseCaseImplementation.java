package co.com.devco.talentpool.videogamestore.application.usecases.customer;

import co.com.devco.talentpool.videogamestore.domain.ports.in.customer.IDeleteCustomerUseCase;
import co.com.devco.talentpool.videogamestore.domain.ports.out.ICustomerRepositoryPort;

public class DeleteCustomerUseCaseImplementation implements IDeleteCustomerUseCase {

    private final ICustomerRepositoryPort iCustomerRepositoryPort;

    public DeleteCustomerUseCaseImplementation(ICustomerRepositoryPort iCustomerRepositoryPort) {
        this.iCustomerRepositoryPort = iCustomerRepositoryPort;
    }

    @Override
    public boolean deleteCustomer(Long id) {
        return iCustomerRepositoryPort.deleteById(id);
    }
}
