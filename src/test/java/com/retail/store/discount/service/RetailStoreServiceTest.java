package com.retail.store.discount.service;

import com.retail.store.discount.RetailStoreDiscountApp;
import com.retail.store.discount.dto.RetailRequestDto;
import com.retail.store.discount.dto.RetailResponseDto;
import com.retail.store.discount.entity.User;
import com.retail.store.discount.enumeration.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RetailStoreDiscountApp.class)
public class RetailStoreServiceTest {


    private RetailStoreService retailStoreService;

    @Mock
    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.retailStoreService = new RetailStoreService(userService);
    }

    @Test
    public void shouldReturnDiscount30Percentage_whenEmployeeUser(){
        User mockedUser = new User(1L, "Mohamed", UserType.EMPLOYEE, LocalDate.of(2019, 3, 25));
        Mockito.when(userService.getUserById(1L)).thenReturn(mockedUser);

        RetailRequestDto retailRequestDto = new RetailRequestDto().setUserId(1L).setAmount(50.0);

        RetailResponseDto retailDiscount = retailStoreService.getRetailDiscount(retailRequestDto);

        assertUser(retailDiscount.getUser(), mockedUser);

        assertThat(retailDiscount.getOriginalAmount()).isEqualTo(50.0);
        assertThat(retailDiscount.getDiscountAmount()).isEqualTo(15.0);
        assertThat(retailDiscount.getNetPayableAmount()).isEqualTo(35.0);

    }


    @Test
    public void shouldReturnDiscount10Percentage_whenAffiliateUser(){
        User mockedUser = new User(1L, "Mohamed", UserType.AFFILIATE, LocalDate.of(2019, 3, 25));
        Mockito.when(userService.getUserById(1L)).thenReturn(mockedUser);

        RetailRequestDto retailRequestDto = new RetailRequestDto().setUserId(1L).setAmount(50.0);

        RetailResponseDto retailDiscount = retailStoreService.getRetailDiscount(retailRequestDto);

        assertUser(retailDiscount.getUser(), mockedUser);

        assertThat(retailDiscount.getOriginalAmount()).isEqualTo(50.0);
        assertThat(retailDiscount.getDiscountAmount()).isEqualTo(5.0);
        assertThat(retailDiscount.getNetPayableAmount()).isEqualTo(45.0);

    }

    @Test
    public void shouldNotReturnDiscount_whenCustomerUserWithLessThan2Years(){
        User mockedUser = new User(1L, "Mohamed", UserType.CUSTOMER, LocalDate.of(2019, 3, 25));
        Mockito.when(userService.getUserById(1L)).thenReturn(mockedUser);

        RetailRequestDto retailRequestDto = new RetailRequestDto().setUserId(1L).setAmount(50.0);

        RetailResponseDto retailDiscount = retailStoreService.getRetailDiscount(retailRequestDto);

        assertUser(retailDiscount.getUser(), mockedUser);

        assertThat(retailDiscount.getOriginalAmount()).isEqualTo(50.0);
        assertThat(retailDiscount.getDiscountAmount()).isEqualTo(0.0);
        assertThat(retailDiscount.getNetPayableAmount()).isEqualTo(50.0);

    }

    @Test
    public void shouldReturnDiscount5Percentage_whenCustomerUserWithMoreThan2Years(){
        User mockedUser = new User(1L, "Mohamed", UserType.CUSTOMER, LocalDate.of(2017, 3, 25));
        Mockito.when(userService.getUserById(1L)).thenReturn(mockedUser);

        RetailRequestDto retailRequestDto = new RetailRequestDto().setUserId(1L).setAmount(50.0);

        RetailResponseDto retailDiscount = retailStoreService.getRetailDiscount(retailRequestDto);

        assertUser(retailDiscount.getUser(), mockedUser);

        assertThat(retailDiscount.getOriginalAmount()).isEqualTo(50.0);
        assertThat(retailDiscount.getDiscountAmount()).isEqualTo(2.5);
        assertThat(retailDiscount.getNetPayableAmount()).isEqualTo(47.5);

    }

    @Test
    public void shouldNotReturnDiscount_whenGroceriesUser(){
        User mockedUser = new User(1L, "Mohamed", UserType.GROCERIES, LocalDate.of(2019, 3, 25));
        Mockito.when(userService.getUserById(1L)).thenReturn(mockedUser);

        RetailRequestDto retailRequestDto = new RetailRequestDto().setUserId(1L).setAmount(50.0);

        RetailResponseDto retailDiscount = retailStoreService.getRetailDiscount(retailRequestDto);

        assertUser(retailDiscount.getUser(), mockedUser);

        assertThat(retailDiscount.getOriginalAmount()).isEqualTo(50.0);
        assertThat(retailDiscount.getDiscountAmount()).isEqualTo(0.0);
        assertThat(retailDiscount.getNetPayableAmount()).isEqualTo(50.0);

    }

    @Test
    public void shouldNotReturnDiscount_whenAnonymousUser(){
        User mockedUser = new User(1L, "Mohamed", null, null);
        Mockito.when(userService.getUserById(1L)).thenReturn(mockedUser);
        RetailRequestDto retailRequestDto = new RetailRequestDto().setUserId(1L).setAmount(50.0);
        RetailResponseDto retailDiscount = retailStoreService.getRetailDiscount(retailRequestDto);

        assertUser(retailDiscount.getUser(), mockedUser);

        assertThat(retailDiscount.getOriginalAmount()).isEqualTo(50.0);
        assertThat(retailDiscount.getDiscountAmount()).isEqualTo(0.0);
        assertThat(retailDiscount.getNetPayableAmount()).isEqualTo(50.0);

    }


    @Test
    public void shouldReturnDiscount_whenBillIsMoreThan100Dollars(){
        User mockedUser = new User(1L, "Mohamed", null, null);
        Mockito.when(userService.getUserById(1L)).thenReturn(mockedUser);
        RetailRequestDto retailRequestDto = new RetailRequestDto().setUserId(1L).setAmount(200.0);
        RetailResponseDto retailDiscount = retailStoreService.getRetailDiscount(retailRequestDto);

        assertUser(retailDiscount.getUser(), mockedUser);

        assertThat(retailDiscount.getOriginalAmount()).isEqualTo(200.0);
        assertThat(retailDiscount.getDiscountAmount()).isEqualTo(10.0);
        assertThat(retailDiscount.getNetPayableAmount()).isEqualTo(190.0);

    }


    @Test
    public void shouldReturnMaxDiscount_whenBillIsMoreThan100Dollars(){
        User mockedUser = new User(1L, "Mohamed", UserType.CUSTOMER, LocalDate.of(2017, 3, 25));
        Mockito.when(userService.getUserById(1L)).thenReturn(mockedUser);
        RetailRequestDto retailRequestDto = new RetailRequestDto().setUserId(1L).setAmount(200.0);
        RetailResponseDto retailDiscount = retailStoreService.getRetailDiscount(retailRequestDto);

        assertUser(retailDiscount.getUser(), mockedUser);

        assertThat(retailDiscount.getOriginalAmount()).isEqualTo(200.0);
        assertThat(retailDiscount.getDiscountAmount()).isEqualTo(10.0);
        assertThat(retailDiscount.getNetPayableAmount()).isEqualTo(190.0);

    }

    private void assertUser(User actualUser, User expectedUser){
        assertThat(actualUser.getId()).isEqualTo(expectedUser.getId());
        assertThat(actualUser.getName()).isEqualTo(expectedUser.getName());
        assertThat(actualUser.getUserType()).isEqualTo(expectedUser.getUserType());
        assertThat(actualUser.getStartDate()).isEqualTo(expectedUser.getStartDate());
    }


}
