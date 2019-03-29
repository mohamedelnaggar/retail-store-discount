package com.retail.store.discount.controller;

import com.retail.store.discount.RetailStoreDiscountApp;
import com.retail.store.discount.enumeration.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RetailStoreDiscountApp.class)
@WebAppConfiguration
public class RetailStoreControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void shouldReturnDiscount30Percentage_whenEmployeeUser() throws Exception {
        String customerUserJson = "{ \"userId\": 1, \"amount\": 50 }";
        this.mockMvc.perform(post("/api/retail-store/calculate-discount")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(customerUserJson)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.id").value(1))
                .andExpect(jsonPath("$.user.userType").value(UserType.EMPLOYEE.toString()))
                .andExpect(jsonPath("$.user.startDate").value("2019-03-25"))
                .andExpect(jsonPath("$.originalAmount").value(50))
                .andExpect(jsonPath("$.discountAmount").value(15))
                .andExpect(jsonPath("$.netPayableAmount").value(35));
    }


    @Test
    public void shouldReturnDiscount10Percentage_whenAffiliateUser() throws Exception{
        String affiliateUserJson = "{ \"userId\": 2, \"amount\": 50 }";
        this.mockMvc.perform(post("/api/retail-store/calculate-discount")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(affiliateUserJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.id").value(2))
                .andExpect(jsonPath("$.user.userType").value(UserType.AFFILIATE.toString()))
                .andExpect(jsonPath("$.user.startDate").value("2019-03-25"))
                .andExpect(jsonPath("$.originalAmount").value(50))
                .andExpect(jsonPath("$.discountAmount").value(5))
                .andExpect(jsonPath("$.netPayableAmount").value(45));

    }

    @Test
    public void shouldNotReturnDiscount_whenCustomerUserWithLessThan2Years() throws Exception{
        String customerUserJson = "{ \"userId\": 3, \"amount\": 50 }";
        this.mockMvc.perform(post("/api/retail-store/calculate-discount")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(customerUserJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.id").value(3))
                .andExpect(jsonPath("$.user.userType").value(UserType.CUSTOMER.toString()))
                .andExpect(jsonPath("$.user.startDate").value("2019-03-25"))
                .andExpect(jsonPath("$.originalAmount").value(50))
                .andExpect(jsonPath("$.discountAmount").value(0))
                .andExpect(jsonPath("$.netPayableAmount").value(50));

    }

    @Test
    public void shouldReturnDiscount5Percentage_whenCustomerUserWithMoreThan2Years() throws Exception{

        String customerUserJson = "{ \"userId\": 4, \"amount\": 50 }";
        this.mockMvc.perform(post("/api/retail-store/calculate-discount")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(customerUserJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.id").value(4))
                .andExpect(jsonPath("$.user.userType").value(UserType.CUSTOMER.toString()))
                .andExpect(jsonPath("$.user.startDate").value("2017-03-25"))
                .andExpect(jsonPath("$.originalAmount").value(50))
                .andExpect(jsonPath("$.discountAmount").value(2.5))
                .andExpect(jsonPath("$.netPayableAmount").value(47.5));
    }


    @Test
    public void shouldNotReturnDiscount_whenGroceriesUser() throws Exception{
        String groceriesUserJson = "{ \"userId\": 5, \"amount\": 50 }";
        this.mockMvc.perform(post("/api/retail-store/calculate-discount")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(groceriesUserJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.id").value(5))
                .andExpect(jsonPath("$.user.userType").value(UserType.GROCERIES.toString()))
                .andExpect(jsonPath("$.user.startDate").value("2019-03-25"))
                .andExpect(jsonPath("$.originalAmount").value(50))
                .andExpect(jsonPath("$.discountAmount").value(0))
                .andExpect(jsonPath("$.netPayableAmount").value(50));

    }

    @Test
    public void shouldNotReturnDiscount_whenAnonymousUser() throws Exception{

        String anonymousUserJson = "{ \"userId\": 6, \"amount\": 50 }";
        this.mockMvc.perform(post("/api/retail-store/calculate-discount")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(anonymousUserJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.id").value(6))
                .andExpect(jsonPath("$.originalAmount").value(50))
                .andExpect(jsonPath("$.discountAmount").value(0))
                .andExpect(jsonPath("$.netPayableAmount").value(50));

    }


    @Test
    public void shouldReturnDiscount_whenBillIsMoreThan100Dollars() throws Exception{

        String groceriesUserJson = "{ \"userId\": 6, \"amount\": 200 }";
        this.mockMvc.perform(post("/api/retail-store/calculate-discount")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(groceriesUserJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.id").value(6))
                .andExpect(jsonPath("$.originalAmount").value(200))
                .andExpect(jsonPath("$.discountAmount").value(10))
                .andExpect(jsonPath("$.netPayableAmount").value(190));

    }


    @Test
    public void shouldReturnBestDiscount_whenBillIsMoreThan100Dollars() throws Exception{

        String customerUserJson = "{ \"userId\": 4, \"amount\": 200 }";
        this.mockMvc.perform(post("/api/retail-store/calculate-discount")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(customerUserJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.id").value(4))
                .andExpect(jsonPath("$.user.userType").value(UserType.CUSTOMER.toString()))
                .andExpect(jsonPath("$.user.startDate").value("2017-03-25"))
                .andExpect(jsonPath("$.originalAmount").value(200))
                .andExpect(jsonPath("$.discountAmount").value(10))
                .andExpect(jsonPath("$.netPayableAmount").value(190));

    }


    @Test
    public void shouldReturnError_whenUserIsNotFound() throws Exception{

        String notFoundUserJson = "{ \"userId\": 20, \"amount\": 200 }";
        this.mockMvc.perform(post("/api/retail-store/calculate-discount")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(notFoundUserJson))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.apiError.errorNumber").value("RETAIL-STORE-ERROR-001"))
                .andExpect(jsonPath("$.apiError.errorMessage").value("user '20' couldn't be found."));

    }
}
