package pja.edu.pl.s26772.restaurantmanagement.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pja.edu.pl.s26772.restaurantmanagement.models.Customer;
import pja.edu.pl.s26772.restaurantmanagement.models.requestDtos.CustomerRequestDto;
import pja.edu.pl.s26772.restaurantmanagement.models.responseDtos.CustomerResponseDto;
import pja.edu.pl.s26772.restaurantmanagement.repositories.CustomerRepository;
import pja.edu.pl.s26772.restaurantmanagement.services.mappers.CustomerMapper;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerResponseDto> getAllCustomers(){
        List<Customer> customers = customerRepository.getAllCustomers();
        return customers.stream().map(customerMapper::customerToResponseDto).toList();
    }

    public Optional<Customer> getCustomer(Long id){
        return customerRepository.findById(id);
    }

    public CustomerResponseDto getResponseDto(Customer customer){
        return customerMapper.customerToResponseDto(customer);
    }

    @Transactional
    public Customer addCustomer(CustomerRequestDto requestDto) {
        return customerRepository.save(customerMapper.requestDtoToCustomer(requestDto));
    }

    @Transactional
    public void deleteCustomer(Customer customer){
        customerRepository.delete(customer);
    }

    @Transactional
    public void updateCustomer(Customer customer,CustomerRequestDto requestDto){
        customer.setAddress(requestDto.getAddress());
        customer.setName(requestDto.getName());
        customer.setPhoneNumber(requestDto.getPhoneNumber());
        customer.setSurname(requestDto.getSurname());
        customerRepository.save(customer);
    }

    public CustomerRequestDto getRequestDto(Customer customer) {
        return customerMapper.customerToRequestDto(customer);
    }
}
