package com.ordinaka.goodreads.services;

import com.ordinaka.goodreads.dtos.AccountCreationRequest;
import com.ordinaka.goodreads.dtos.UserDto;
import com.ordinaka.goodreads.exceptions.GoodReadsException;

public interface UserService {
    UserDto createUserAccount(AccountCreationRequest accountCreationRequest)throws GoodReadsException;
}
