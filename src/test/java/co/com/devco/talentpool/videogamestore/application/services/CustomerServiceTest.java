package co.com.devco.talentpool.videogamestore.application.services;


import co.com.devco.talentpool.videogamestore.domain.models.Customer;
import co.com.devco.talentpool.videogamestore.domain.ports.in.customer.ICreateCustomerUseCase;
import co.com.devco.talentpool.videogamestore.domain.ports.in.customer.IDeleteCustomerUseCase;
import co.com.devco.talentpool.videogamestore.domain.ports.in.customer.IRetrieveCustomerUseCase;
import co.com.devco.talentpool.videogamestore.domain.ports.in.customer.IUpdateCustomerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class CustomerServiceTest {
    private CustomerService customerService;
    private ICreateCustomerUseCase mockCreateCustomer;
    private IDeleteCustomerUseCase mockDeleteCustomer;
    private IRetrieveCustomerUseCase mockRetrieveCustomer;
    private IUpdateCustomerUseCase mockUpdateCustomer;

    @BeforeEach
    void setUp() {
        mockCreateCustomer = mock(ICreateCustomerUseCase.class);
        mockDeleteCustomer = mock(IDeleteCustomerUseCase.class);
        mockRetrieveCustomer = mock(IRetrieveCustomerUseCase.class);
        mockUpdateCustomer = mock(IUpdateCustomerUseCase.class);
        customerService = new CustomerService(mockCreateCustomer, mockDeleteCustomer, mockRetrieveCustomer, mockUpdateCustomer);
    }

    @Test
    void testCreateCustomer() {
        Customer customer = new Customer(1L, "John", "Doe");
        when(mockCreateCustomer.createCustomer(customer)).thenReturn(customer);

        Customer result = customerService.createCustomer(customer);

        assertThat(result).isEqualTo(customer);
        verify(mockCreateCustomer).createCustomer(customer);
    }

    @Test
    void testDeleteCustomer() {
        Long customerId = 1L;
        when(mockDeleteCustomer.deleteCustomer(customerId)).thenReturn(true);

        boolean result = customerService.deleteCustomer(customerId);

        assertThat(result).isTrue();
        verify(mockDeleteCustomer).deleteCustomer(customerId);
    }

    @Test
    void testGetCustomer() {
        Long customerId = 1L;
        Customer customer = new Customer(1L, "John", "Doe");
        when(mockRetrieveCustomer.getCustomer(customerId)).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerService.getCustomer(customerId);

        assertThat(result).isPresent().contains(customer);
        verify(mockRetrieveCustomer).getCustomer(customerId);
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> customers = List.of(new Customer(1L, "John", "Doe"));
        when(mockRetrieveCustomer.getAllCustomers()).thenReturn(customers);

        List<Customer> result = customerService.getAllCustomers();

        assertThat(result).isEqualTo(customers);
        verify(mockRetrieveCustomer).getAllCustomers();
    }

    @Test
    void testUpdateCustomer() {
        Long customerId = 1L;
        Customer updatedCustomer = new Customer(1L, "Jane", "Doe");
        when(mockUpdateCustomer.updateCustomer(customerId, updatedCustomer)).thenReturn(Optional.of(updatedCustomer));

        Optional<Customer> result = customerService.updateCustomer(customerId, updatedCustomer);

        assertThat(result).isPresent().contains(updatedCustomer);
        verify(mockUpdateCustomer).updateCustomer(customerId, updatedCustomer);
    }

    @Test
    void testUpdateCustomer_NotFound() {
        Long customerId = 1L;
        Customer updatedCustomer = new Customer(1L, "Jane", "Doe");
        when(mockUpdateCustomer.updateCustomer(customerId, updatedCustomer)).thenReturn(Optional.empty());

        Optional<Customer> result = customerService.updateCustomer(customerId, updatedCustomer);

        assertThat(result).isEmpty();
        verify(mockUpdateCustomer).updateCustomer(customerId, updatedCustomer);
    }
}
