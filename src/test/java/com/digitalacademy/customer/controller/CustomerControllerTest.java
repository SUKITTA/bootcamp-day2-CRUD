package com.digitalacademy.customer.controller;


import com.digitalacademy.customer.model.Customer;
import com.digitalacademy.customer.service.CustomerService;
import com.digitalacademy.customer.support.CustomerSupportTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    private MockMvc mvc;
    public static final String urlCustomerList = "/customer/list/";
    public static final String urlCustomer = "/customer/";

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        customerController = new CustomerController(customerService);
        mvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

//    @DisplayName("Test get customer should return customer list")
//    @Test
//    void testGetCustomerList() throws Exception{
//        when(customerService.getCustomerList()).thenReturn(CustomerSupportTest.getCustomerList());
//
//        MvcResult mvcResult = mvc.perform(get(urlCustomerList))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andReturn();
//
//        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
//        assertEquals("1", jsonArray.getJSONObject(0).get("id").toString());
//        assertEquals("jay", jsonArray.getJSONObject(0).get("firstName"));
//        assertEquals("park", jsonArray.getJSONObject(0).get("lastName"));
//        assertEquals("jpark@hotmail.com", jsonArray.getJSONObject(0).get("email"));
//        assertEquals("0869999999", jsonArray.getJSONObject(0).get("phoneNumber"));
//        assertEquals(20, jsonArray.getJSONObject(0).get("age"));
//
//        assertEquals("2", jsonArray.getJSONObject(0).get("id").toString());
//        assertEquals("gray", jsonArray.getJSONObject(0).get("firstName"));
//        assertEquals("bark", jsonArray.getJSONObject(0).get("lastName"));
//        assertEquals("gbark@hotmail.com", jsonArray.getJSONObject(0).get("email"));
//        assertEquals("0869999999", jsonArray.getJSONObject(0).get("phoneNumber"));
//        assertEquals(20, jsonArray.getJSONObject(0).get("age"));
//
//    }

//    @DisplayName("Test get customer by id should return customer")
//    @Test
//    void testGetCustomerById() throws Exception{
//        Long reqId = 1L;
//        when(customerService.getCustomer(reqId)).thenReturn(CustomerSupportTest.getNewCustomer());
//
//        MvcResult mvcResult = mvc.perform(get(urlCustomer + "/" + reqId))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andReturn();
//
//        org.json.JSONObject json = new JSONObject(mvcResult.getResponse().getContentAsString());
//
//        assertEquals("1", json.get("id").toString());
//        assertEquals("Noon1", json.get("firstName"));
//        assertEquals("Bow", json.get("lastName"));
//        assertEquals("noon@hotmail.com", json.get("email"));
//        assertEquals("1234566", json.get("phoneNumber"));
//        assertEquals(18, json.get("age"));
//
//    }

    @DisplayName("Test get customer by id should return not found")
    @Test
    void testGetCustomerByIdNotFound() throws Exception{
        Long reqId = 5L;
        MvcResult mvcResult = mvc.perform(get(urlCustomer + "/" + reqId))
                .andExpect(status().isNotFound())
                .andReturn();
    }

//    @DisplayName("Test get customer by name should return customer")
//    @Test
//    void testGetCustomerByName() throws Exception {
//        String reqName = "jay";
//        when(customerService.getCustomerName(reqName)).thenReturn(CustomerSupportTest.getCustomerNameJay());
//
//        when(customerService.getCustomerName(reqName)).thenReturn(CustomerSupportTest.getCustomerNameJay());
//
//        MvcResult mvcResult = mvc.perform(get(urlCustomer + "?name=" + reqName))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andReturn();
//
//        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
//
//        assertEquals("1", jsonArray.getJSONObject(0).get("id").toString());
//        assertEquals("jay", jsonArray.getJSONObject(0).get("firstName"));
//        assertEquals("park", jsonArray.getJSONObject(0).get("lastName"));
//        assertEquals("jpark@hotmail.com", jsonArray.getJSONObject(0).get("email"));
//        assertEquals("0869999999", jsonArray.getJSONObject(0).get("phoneNumber"));
//        assertEquals(20, jsonArray.getJSONObject(0).get("age"));
//
//        assertEquals("2", jsonArray.getJSONObject(1).get("id").toString());
//        assertEquals("jay", jsonArray.getJSONObject(1).get("firstName"));
//        assertEquals("bark", jsonArray.getJSONObject(1).get("lastName"));
//        assertEquals("jbark@hotmail.com", jsonArray.getJSONObject(1).get("email"));
//        assertEquals("0869999999", jsonArray.getJSONObject(1).get("phoneNumber"));
//        assertEquals(20, jsonArray.getJSONObject(1).get("age"));
//
//    }

    @DisplayName("Test update customer should return success")
    @Test
    void  testUpdateCustomer() throws Exception{
        Customer reqCustomer = CustomerSupportTest.getUpdateCustomer();
        Long reqId = 1L;

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(reqCustomer);

        when(customerService.updateCustomer(reqId, reqCustomer)).thenReturn(CustomerSupportTest.getUpdateCustomer());

        MvcResult mvcResult = mvc.perform(put(urlCustomer + "/" + reqId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        org.json.JSONObject json = new org.json.JSONObject(mvcResult.getResponse().getContentAsString());

        assertEquals("1", json.get("id").toString());
        assertEquals("Noon2", json.get("firstName"));
        assertEquals("Bow", json.get("lastName"));
        assertEquals("1234566", json.get("phoneNumber"));
        assertEquals("noon@gmail.com", json.get("email"));
        assertEquals(18, json.get("age"));

        verify(customerService, times(1)).updateCustomer(reqId, reqCustomer);
    }

    @DisplayName("Test update customer should return id not found")
    @Test
    void testUpdateCustomerIdNotFound() throws Exception{
        Customer reqCustomer = CustomerSupportTest.getUpdateCustomer();
        Long reqId = 1L;

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(reqCustomer);

        when(customerService.updateCustomer(reqId, reqCustomer)).thenReturn(null);

        MvcResult mvcResult = mvc.perform(put(urlCustomer + "/" + reqId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isNotFound())
                .andReturn();

        verify(customerService, times(1)).updateCustomer(reqId, reqCustomer);

    }

    @DisplayName("Test update customer should return id is not send")
    @Test
    void testUpdateCustomerIdIsNotSend() throws Exception{
        Customer reqCustomer = CustomerSupportTest.getUpdateCustomer();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(reqCustomer);

        MvcResult mvcResult = mvc.perform(put(urlCustomer + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }


    @DisplayName("Test create customer with path id in not sent")
    @Test
    void testUpdateCustomerWithPathIsNotSent() throws Exception{
        Customer reqCustomer = CustomerSupportTest.getNewCustomer();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String reqJson = ow.writeValueAsString(reqCustomer);

        MvcResult mvcResult = mvc.perform(put(urlCustomer + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(reqJson))
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    @DisplayName("Test delete customer should SUCCESS")
    @Test
    void testDeleteCustomer() throws Exception{
        Long reqId = 4L;

        when(customerService.deleteCustomer(reqId)).thenReturn(true);

        MvcResult mvcResult = mvc.perform(delete(urlCustomer + "/" + reqId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(customerService, times(1)).deleteCustomer(reqId);
    }

    @DisplayName("Test delete customer are not found")
    @Test
    void testDeleteIsNotFound() throws Exception{
        Long reqId = 4L;

        when(customerService.deleteCustomer(reqId)).thenReturn(false);

        MvcResult mvcResult = mvc.perform(delete(urlCustomer + "/" + reqId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        verify(customerService, times(1)).deleteCustomer(reqId);
    }
}
