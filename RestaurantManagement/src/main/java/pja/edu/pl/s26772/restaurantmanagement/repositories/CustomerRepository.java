package pja.edu.pl.s26772.restaurantmanagement.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pja.edu.pl.s26772.restaurantmanagement.models.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    @Query("SELECT c FROM Customer c")
    List<Customer> getAllCustomers();

}
