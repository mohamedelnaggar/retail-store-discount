package com.retail.store.discount.service;

import com.retail.store.discount.RetailStoreDiscountApp;
import com.retail.store.discount.entity.User;
import com.retail.store.discount.enumeration.UserType;
import com.retail.store.discount.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = RetailStoreDiscountApp.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.userService = new UserService(userRepository);
    }

    @Test
    public void shouldReturnCollection_whenGetAllUsersIsCalled(){

        List<User> mockedUserList = new ArrayList<>();
        mockedUserList.add(new User(1L, "Mohamed", UserType.EMPLOYEE, LocalDate.now()));
        mockedUserList.add(new User(2L, "Mohamed", UserType.EMPLOYEE, LocalDate.now()));
        mockedUserList.add(new User(3L, "Mohamed", UserType.EMPLOYEE, LocalDate.now()));

        Mockito.when(userRepository.findAll()).thenReturn(mockedUserList);

        Collection<User> result = userService.getAllUsers();
        assertThat(result.size()).isEqualTo(3);

    }

    @Test
    public void shouldReturnUser_whenGetUserDetailsIsCalled(){

        User mockedUser = new User(1L, "Mohamed", UserType.EMPLOYEE, LocalDate.now());

        Mockito.when(userRepository.findOneById(1L)).thenReturn(mockedUser);

        User user = userService.getUserById(1L);
        assertThat(user.getUserType()).isEqualTo(UserType.EMPLOYEE);
        assertThat(user.getName()).isEqualTo("Mohamed");

    }
}
