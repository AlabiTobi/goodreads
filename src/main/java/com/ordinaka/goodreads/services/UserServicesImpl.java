package com.ordinaka.goodreads.services;

import com.ordinaka.goodreads.dtos.AccountCreationRequest;
import com.ordinaka.goodreads.dtos.UserDto;
import com.ordinaka.goodreads.exceptions.GoodReadsException;
import com.ordinaka.goodreads.models.User;
import com.ordinaka.goodreads.repositories.UserRepository;
import com.ordinaka.goodreads.utils.AccountValidation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserService{

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserServicesImpl(UserRepository userRepository){
        this.userRepository= userRepository;
        modelMapper= new ModelMapper();
    }

    @Override
    public UserDto createUserAccount(AccountCreationRequest accountCreationRequest) throws GoodReadsException{
        AccountValidation.validate(accountCreationRequest);
        User user = User.builder()
                .firstName(accountCreationRequest.getFirstName())
                .lastName(accountCreationRequest.getLastName())
                .email(accountCreationRequest.getEmail())
                .password(accountCreationRequest.getPassword())
                .build();
    User savedUser = userRepository.save(user);
    return modelMapper.map(savedUser, UserDto.class);
    }
}
