package co.com.devco.talentpool.videogamestore.infrastructure.repositories.customer;

import co.com.devco.talentpool.videogamestore.domain.models.Customer;
import co.com.devco.talentpool.videogamestore.domain.ports.out.ICustomerRepositoryPort;
import co.com.devco.talentpool.videogamestore.infrastructure.entities.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaCustomerRepositoryAdapter implements ICustomerRepositoryPort {

    private final IJpaCustomerRepository iJpaCustomerRepository;

    public JpaCustomerRepositoryAdapter(IJpaCustomerRepository iJpaCustomerRepository) {
        this.iJpaCustomerRepository = iJpaCustomerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = CustomerEntity.fromDomainModel(customer);
        CustomerEntity savedCustomerEntity = iJpaCustomerRepository.save(customerEntity);
        return savedCustomerEntity.toDomainModel();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return iJpaCustomerRepository.findById(id).map(CustomerEntity::toDomainModel);
    }

    @Override
    public List<Customer> findAll() {
        return iJpaCustomerRepository.findAll().stream().map(CustomerEntity::toDomainModel).toList();
    }

    @Override
    public Optional<Customer> update(Long id, Customer customer) {
        if (iJpaCustomerRepository.existsById(customer.getId())){
            CustomerEntity customerEntity = CustomerEntity.fromDomainModel(customer);
            CustomerEntity updateCustomerEntity = iJpaCustomerRepository.save(customerEntity);
            return Optional.of(updateCustomerEntity.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        if (iJpaCustomerRepository.existsById(id)){
            iJpaCustomerRepository.deleteById(id);
            return  true;
        }
        return false;
    }
}
