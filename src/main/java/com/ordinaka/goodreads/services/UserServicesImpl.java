package com.ordinaka.goodreads.services;

import com.ordinaka.goodreads.controllers.requestsAndResponse.AccountCreationRequest;
import com.ordinaka.goodreads.controllers.requestsAndResponse.UpdateRequest;
import com.ordinaka.goodreads.dtos.UserDto;
import com.ordinaka.goodreads.exceptions.GoodReadsException;
import com.ordinaka.goodreads.models.User;
import com.ordinaka.goodreads.repositories.UserRepository;
import com.ordinaka.goodreads.utils.AccountValidation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

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

    @Override
    public UserDto findUserById(String userId) throws GoodReadsException {
        User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(
                () -> new GoodReadsException(String.format("User with id %s not found", userId))
        );
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class)).toList();
    }


    @Override
    public UserDto updateUserProfile(String id, UpdateRequest updateRequest) throws GoodReadsException {
        User user = userRepository.findById(Long.valueOf(id)).orElseThrow(
                () -> new GoodReadsException("user id not found")
        );
        User userToSave = modelMapper.map(updateRequest,User.class);
        userToSave.setId(user.getId());
        userRepository.save(userToSave);
        return modelMapper.map(userToSave, UserDto.class);
    }
}
