package co.com.devco.talentpool.videogamestore.infrastructure.entities;

import co.com.devco.talentpool.videogamestore.domain.models.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;

    public CustomerEntity(Long id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    public static CustomerEntity fromDomainModel(Customer customer){
        return new CustomerEntity(customer.getId(), customer.getName(), customer.getLastName());
    }

    public Customer toDomainModel(){
        return new Customer(id, name, lastName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
