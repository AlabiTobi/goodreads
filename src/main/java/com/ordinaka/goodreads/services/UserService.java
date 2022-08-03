package com.ordinaka.goodreads.services;

import com.ordinaka.goodreads.controllers.requestsAndResponse.AccountCreationRequest;
import com.ordinaka.goodreads.controllers.requestsAndResponse.UpdateRequest;
import com.ordinaka.goodreads.dtos.UserDto;
import com.ordinaka.goodreads.exceptions.GoodReadsException;

import java.util.List;

public interface UserService {
    UserDto createUserAccount(AccountCreationRequest accountCreationRequest)throws GoodReadsException;

    UserDto findUserById(String userId) throws GoodReadsException;

    List<UserDto> findAll();
    UserDto updateUserProfile(String id, UpdateRequest updateRequest) throws GoodReadsException;
}
