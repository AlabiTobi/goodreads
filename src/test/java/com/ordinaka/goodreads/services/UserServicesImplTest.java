package com.ordinaka.goodreads.services;

import com.ordinaka.goodreads.controllers.requestsAndResponse.AccountCreationRequest;
import com.ordinaka.goodreads.dtos.UserDto;
import com.ordinaka.goodreads.exceptions.GoodReadsException;
import com.ordinaka.goodreads.models.User;
import com.ordinaka.goodreads.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServicesImplTest {
    @Autowired
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServicesImpl(userRepository);
    }

    @Test
    void testThatUserCanCreateAccount() throws GoodReadsException {
        AccountCreationRequest accountCreationRequest =
                new AccountCreationRequest("Firstname", "Lastname", "testemail@gmail.com","password" );
        UserDto userDto = userService.createUserAccount(accountCreationRequest);

        Optional<User> optionalUser = userRepository.findById(userDto.getId());
        assertThat(optionalUser.isPresent()).isEqualTo(true);
        assertThat(optionalUser.get().getFirstName()).isEqualTo("Firstname");
        assertThat(optionalUser.get().getLastName()).isEqualTo("Lastname");
        assertThat(optionalUser.get().getEmail()).isEqualTo("testemail@gmail.com");
        assertThat(optionalUser.get().getPassword()).isEqualTo("password");
    }

    @Test
    void testThatUserEmailIsUnique() throws GoodReadsException {
        AccountCreationRequest firstAccountCreationRequest =
                new AccountCreationRequest("Firstname", "Lastname", "testemail@gmail.com","password" );
        UserDto userDto = userService.createUserAccount(firstAccountCreationRequest);

        AccountCreationRequest secondAccountCreationRequest =
                new AccountCreationRequest("Amaka", "Chopper", "testemail@gmail.com","password1234" );
        assertThrows(GoodReadsException.class, ()-> userService.createUserAccount(secondAccountCreationRequest));
    }


}