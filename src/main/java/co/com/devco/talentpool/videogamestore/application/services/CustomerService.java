package co.com.devco.talentpool.videogamestore.application.services;

import co.com.devco.talentpool.videogamestore.domain.models.Customer;
import co.com.devco.talentpool.videogamestore.domain.ports.in.customer.ICreateCustomerUseCase;
import co.com.devco.talentpool.videogamestore.domain.ports.in.customer.IDeleteCustomerUseCase;
import co.com.devco.talentpool.videogamestore.domain.ports.in.customer.IRetrieveCustomerUseCase;
import co.com.devco.talentpool.videogamestore.domain.ports.in.customer.IUpdateCustomerUseCase;
import java.util.List;
import java.util.Optional;

public class CustomerService implements ICreateCustomerUseCase, IDeleteCustomerUseCase, IRetrieveCustomerUseCase, IUpdateCustomerUseCase {

    private final ICreateCustomerUseCase iCreateCustomerUseCase;
    private final IDeleteCustomerUseCase iDeleteCustomerUseCase;
    private final IRetrieveCustomerUseCase iRetrieveCustomerUseCase;
    private final IUpdateCustomerUseCase iUpdateCustomerUseCase;

    public CustomerService(ICreateCustomerUseCase iCreateCustomerUseCase, IDeleteCustomerUseCase iDeleteCustomerUseCase, IRetrieveCustomerUseCase iRetrieveCustomerUseCase, IUpdateCustomerUseCase iUpdateCustomerUseCase) {
        this.iCreateCustomerUseCase = iCreateCustomerUseCase;
        this.iDeleteCustomerUseCase = iDeleteCustomerUseCase;
        this.iRetrieveCustomerUseCase = iRetrieveCustomerUseCase;
        this.iUpdateCustomerUseCase = iUpdateCustomerUseCase;
    }


    @Override
    public Customer createCustomer(Customer customer) {
        return iCreateCustomerUseCase.createCustomer(customer);
    }

    @Override
    public boolean deleteCustomer(Long id) {
        return iDeleteCustomerUseCase.deleteCustomer(id);
    }

    @Override
    public Optional<Customer> getCustomer(Long id) {
        return iRetrieveCustomerUseCase.getCustomer(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return iRetrieveCustomerUseCase.getAllCustomers();
    }

    @Override
    public Optional<Customer> updateCustomer(Long id, Customer updateCustomer) {
        return iUpdateCustomerUseCase.updateCustomer(id, updateCustomer);
    }
}
