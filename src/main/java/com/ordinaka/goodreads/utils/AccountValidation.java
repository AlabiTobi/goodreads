package com.ordinaka.goodreads.utils;

import com.ordinaka.goodreads.controllers.requestsAndResponse.AccountCreationRequest;
import com.ordinaka.goodreads.exceptions.GoodReadsException;
import com.ordinaka.goodreads.models.User;
import com.ordinaka.goodreads.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountValidation {
    private static UserRepository userRepository;
    public AccountValidation(UserRepository userRepository){
        AccountValidation.userRepository= userRepository;
    }

    public static void validate(AccountCreationRequest accountCreationRequest)
    throws GoodReadsException{
        log.info("In validate Method");

        User user= userRepository.findUserByEmail(accountCreationRequest.getEmail()).orElse(null);
        if(user !=null){
            throw new GoodReadsException("user email already exists");
        }

    }
}
