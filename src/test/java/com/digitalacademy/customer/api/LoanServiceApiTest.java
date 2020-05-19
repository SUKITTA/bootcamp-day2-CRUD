package com.digitalacademy.customer.api;

import com.digitalacademy.customer.model.response.GetLoanInfoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import static com.sun.javaws.JnlpxArgs.verify;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LoanServiceApiTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    LoanApi loanApi;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        loanApi = new LoanApi(restTemplate);
    }

    public static ResponseEntity<String> prepareResponseSuccess(){
        return ResponseEntity.ok("{\n" +
                "    \"status\": {\n" +
                "        \"code\": \"0\",\n" +
                "        \"message\": \"success\"\n" +
                "    },\n" +
                "    \"data\": {\n" +
                "        \"id\": 1,\n" +
                "        \"status\": \"OK\",\n" +
                "        \"account_payable\": \"102-222-2200\",\n" +
                "        \"account_receivable\": \"102-333-2200\",\n" +
                "        \"principle_amount\": 3400000.0\n" +
                "    }\n" +
                "}");
    }


    public static ResponseEntity<String> prepareResponseEntityLOAN4002(){
        return ResponseEntity.ok("{\n" +
                "    \"status\": {\n" +
                "        \"code\": \"LOAN4002\",\n" +
                "        \"message\": \"Loan information not found\"\n" +
                "    }\n" +
                "}");
    }
    public static ResponseEntity<String> prepareResponseEntityLOAN4001(){
        return ResponseEntity.ok("{\n" +
                "    \"status\": {\n" +
                "        \"code\": \"LOAN4001\",\n" +
                "        \"message\": \"Cannot get loan information\"\n" +
                "    },\n" +
                "    \"data\": \"Cannot get loan information\"\n" +
                "}");
    }

    @DisplayName("Test get loan info should return loan information")
    @Test
    void testGetLoanInfo() throws Exception {
        Long reqId = 1L;

        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(prepareResponseSuccess());

        GetLoanInfoResponse resp = loanApi.getLoanInfo(reqId);

        assertEquals("1", resp.getId().toString());
        assertEquals("OK",resp.getStatus());
        assertEquals("102-222-2200", resp.getAccountPayable());
        assertEquals("102-333-2200",resp.getAccountReceivable());
        assertEquals(34000.00, resp.getPrincipleAmount());

        verify(restTemplate, times(1))
                .exchange(ArgumentMatchers.<RequestEntity<String>>any(), ArgumentMatchers.<Class<String>>any());
    }

    @DisplayName("Test get loan info should return Internal server error 4002")
    @Test
    void testGetLoanInfoInternalServer4002() throws Exception {
        Long reqId = 2L;

        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(prepareResponseEntityLOAN4002());

        GetLoanInfoResponse resp = loanApi.getLoanInfo(reqId);

        assertNull(resp.getId());
        assertNull(resp.getStatus());
        assertNull(resp.getAccountPayable());
        assertNull(resp.getAccountReceivable());
        assertEquals(0, resp.getPrincipleAmount());

    }

    @DisplayName("Test get loan info should return Internal server error 4001")
    @Test
    void testGetLoanInfoInternalServer4001() throws Exception {
        Long reqId = 3L;

        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(prepareResponseEntityLOAN4001());

        GetLoanInfoResponse resp = loanApi.getLoanInfo(reqId);

        assertNull(resp.getId());
        assertNull(resp.getStatus());
        assertNull(resp.getAccountPayable());
        assertNull(resp.getAccountReceivable());
        assertEquals(0, resp.getPrincipleAmount());

    }
}
