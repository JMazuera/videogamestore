package co.com.devco.talentpool.videogamestore.application.usecases;

import co.com.devco.talentpool.videogamestore.application.usecases.customer.CreateCustomerUseCaseImplementation;
import co.com.devco.talentpool.videogamestore.application.usecases.customer.DeleteCustomerUseCaseImplementation;
import co.com.devco.talentpool.videogamestore.application.usecases.customer.RetrieveCustomerUseCaseImplementation;
import co.com.devco.talentpool.videogamestore.application.usecases.customer.UpdateCustomerUseCaseImplementation;
import co.com.devco.talentpool.videogamestore.domain.models.Customer;
import co.com.devco.talentpool.videogamestore.domain.ports.out.ICustomerRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerUseCasesTest {
    @Mock
    private ICustomerRepositoryPort mockCustomerRepositoryPort;

    @InjectMocks
    private CreateCustomerUseCaseImplementation createCustomerUseCase;

    @InjectMocks
    private DeleteCustomerUseCaseImplementation deleteCustomerUseCase;

    @InjectMocks
    private RetrieveCustomerUseCaseImplementation retrieveCustomerUseCase;

    @InjectMocks
    private UpdateCustomerUseCaseImplementation updateCustomerUseCase;

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer(1L, "Juan", "Pérez");
    }

    @Test
    void testCreateCustomer() {
        when(mockCustomerRepositoryPort.save(customer)).thenReturn(customer);

        Customer result = createCustomerUseCase.createCustomer(customer);

        assertThat(result).isNotNull()
                .isEqualTo(customer);

        verify(mockCustomerRepositoryPort).save(customer);
    }

    @Test
    void testDeleteCustomer() {
        Long customerId = 1L;
        when(mockCustomerRepositoryPort.deleteById(customerId)).thenReturn(true);

        boolean result = deleteCustomerUseCase.deleteCustomer(customerId);

        assertThat(result).isTrue();
        verify(mockCustomerRepositoryPort).deleteById(customerId);
    }

    @Test
    void testGetCustomer() {
        Long customerId = 1L;
        when(mockCustomerRepositoryPort.findById(customerId)).thenReturn(Optional.of(customer));

        Optional<Customer> result = retrieveCustomerUseCase.getCustomer(customerId);

        assertThat(result)
                .isPresent()
                .contains(customer);

        verify(mockCustomerRepositoryPort).findById(customerId);
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> customers = List.of(customer);
        when(mockCustomerRepositoryPort.findAll()).thenReturn(customers);

        List<Customer> result = retrieveCustomerUseCase.getAllCustomers();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .contains(customer);

        verify(mockCustomerRepositoryPort).findAll();
    }

    @Test
    void testUpdateCustomer() {
        Long customerId = 1L;
        Customer updatedCustomer = new Customer(1L, "Juan", "González");
        when(mockCustomerRepositoryPort.update(customerId, updatedCustomer)).thenReturn(Optional.of(updatedCustomer));

        Optional<Customer> result = updateCustomerUseCase.updateCustomer(customerId, updatedCustomer);

        assertThat(result)
                .isPresent()
                .contains(updatedCustomer);

        verify(mockCustomerRepositoryPort).update(customerId, updatedCustomer);
    }
}
