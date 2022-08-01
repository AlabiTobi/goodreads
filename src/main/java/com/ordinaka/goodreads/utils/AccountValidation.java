package com.ordinaka.goodreads.utils;

import com.ordinaka.goodreads.dtos.AccountCreationRequest;
import com.ordinaka.goodreads.exceptions.GoodReadsException;
import com.ordinaka.goodreads.models.User;
import com.ordinaka.goodreads.repositories.UserRepository;

public class AccountValidation {
    private static UserRepository userRepository;
    public AccountValidation(UserRepository userRepository){
        AccountValidation.userRepository= userRepository;
    }

    public static void validate(AccountCreationRequest accountCreationRequest)
    throws GoodReadsException{
        User user= userRepository.findUserByEmail(accountCreationRequest.getEmail()).orElse(null);
        if(user !=null){
            throw new GoodReadsException("user email already exists");
        }

    }
}
