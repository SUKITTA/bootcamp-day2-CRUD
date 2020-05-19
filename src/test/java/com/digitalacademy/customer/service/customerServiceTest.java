package com.digitalacademy.customer.service;

import com.digitalacademy.customer.model.Customer;
import com.digitalacademy.customer.repositories.CustomerRepository;
import com.digitalacademy.customer.support.CustomerSupportTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class customerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerService(customerRepository);
    }

    @DisplayName("Test gen all customer should return list")
    @Test
    void testGetAllCustomer(){
        when(customerRepository.findAll()).thenReturn(CustomerSupportTest.getCustomerList());
        List<Customer> resp = customerService.getCustomerList();

        assertEquals(1, resp.get(0).getId().intValue());
        assertEquals("jay", resp.get(0).getFirstName());
        assertEquals("park", resp.get(0).getLastName());
        assertEquals("jpark@hotmail.com", resp.get(0).getEmail());
        assertEquals("0869999999", resp.get(0).getPhoneNumber());
        assertEquals(20, resp.get(0).getAge().intValue());

        assertEquals(2, resp.get(1).getId().intValue());
        assertEquals("jay", resp.get(1).getFirstName());
        assertEquals("bark", resp.get(1).getLastName());
        assertEquals("jbark@hotmail.com", resp.get(1).getEmail());
        assertEquals("0869999999", resp.get(1).getPhoneNumber());
        assertEquals(20, resp.get(1).getAge().intValue());
    }



    @DisplayName("Test gen all customer by id should return list")
    @Test
    void testGetAllCustomerById(){
        Long reqParam = 1L;
        when(customerRepository.findAllById(reqParam))
                .thenReturn(CustomerSupportTest.getCustomerList().get(0));
        Customer resp = customerService.getCustomer(reqParam);

        assertEquals(1, resp.getId().intValue());
        assertEquals("jay", resp.getFirstName());
        assertEquals("park", resp.getLastName());
        assertEquals("jpark@hotmail.com", resp.getEmail());
        assertEquals("0869999999", resp.getPhoneNumber());

    }


    @DisplayName("Test gen all customer by id should return")
    @Test
    void testGetAllCustomerByName(){
        String name = "jay";
        when(customerRepository.findByFirstName(name))
                .thenReturn(CustomerSupportTest.getCustomerList());
        List<Customer> resp = customerService.getCustomerName(name);

        assertEquals(1, resp.get(0).getId().intValue());
        assertEquals("jay", resp.get(0).getFirstName());
        assertEquals("park", resp.get(0).getLastName());
        assertEquals("jpark@hotmail.com", resp.get(0).getEmail());
        assertEquals("0869999999", resp.get(0).getPhoneNumber());
        assertEquals(20, resp.get(0).getAge().intValue());

        assertEquals(2, resp.get(1).getId().intValue());
        assertEquals("jay", resp.get(1).getFirstName());
        assertEquals("bark", resp.get(1).getLastName());
        assertEquals("jbark@hotmail.com", resp.get(1).getEmail());
        assertEquals("0869999999", resp.get(1).getPhoneNumber());
        assertEquals(20, resp.get(1).getAge().intValue());

    }

    @DisplayName("Test create customer should return new customer")
    @Test
    void testCreateCustomer(){
        Customer reqCustomer = new Customer();
        reqCustomer.setFirstName("Noon");
        reqCustomer.setLastName("Bow");
        reqCustomer.setEmail("bow@test.com");
        reqCustomer.setPhoneNumber("0869999999");
        reqCustomer.setAge(20);

    }


}
