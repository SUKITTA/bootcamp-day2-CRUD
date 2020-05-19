package com.digitalacademy.customer.support;

import com.digitalacademy.customer.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerSupportTest {

    public static Customer createNewCustomer() {
        Customer customer = new Customer();

        customer.setFirstName("Madrid");
        customer.setLastName("bababi");
        customer.setEmail("mb@hotmail.com");
        customer.setPhoneNumber("0869999999");
        customer.setAge(20);

        return customer;
    }

    public static Customer responseCreateNewCustomer() {
        Customer customer = new Customer();

        customer.setId(8L);
        customer.setFirstName("Madrid");
        customer.setLastName("bababi");
        customer.setEmail("mb@hotmail.com");
        customer.setPhoneNumber("0869999999");
        customer.setAge(20);

        return customer;
    }


    public static Customer getNewCustomer() {
        Customer customer = new Customer();

        customer.setId(1L);
        customer.setFirstName("bong");
        customer.setLastName("bababi");
        customer.setEmail("bongbong@hotmail.com");
        customer.setPhoneNumber("0869999999");
        customer.setAge(20);

        return customer;
    }

    public static Customer getUpdateCustomer() {
        Customer customer = new Customer();

        customer.setId(1L);
        customer.setFirstName("bong2");
        customer.setLastName("bababi");
        customer.setEmail("bongbong@hotmail.com");
        customer.setPhoneNumber("0869999999");
        customer.setAge(20);

        return customer;
    }

    public static List<Customer> getCustomerList() {
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();

        customer.setId(1L);
        customer.setFirstName("jay");
        customer.setLastName("park");
        customer.setEmail("jpark@hotmail.com");
        customer.setPhoneNumber("0869999999");
        customer.setAge(20);
        customerList.add(customer);

        customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("gray");
        customer.setLastName("bark");
        customer.setEmail("gbark@hotmail.com");
        customer.setPhoneNumber("0869999999");
        customer.setAge(20);
        customerList.add(customer);

        return customerList;
    }

    public static List<Customer> getCustomerNameJay(){
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();

        customer.setId(1L);
        customer.setFirstName("jay");
        customer.setLastName("park");
        customer.setEmail("jpark@hotmail.com");
        customer.setPhoneNumber("0869999999");
        customer.setAge(20);
        customerList.add(customer);

        customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("jay");
        customer.setLastName("bark");
        customer.setEmail("jbark@hotmail.com");
        customer.setPhoneNumber("0869999999");
        customer.setAge(20);
        customerList.add(customer);

        return customerList;
    }

}
