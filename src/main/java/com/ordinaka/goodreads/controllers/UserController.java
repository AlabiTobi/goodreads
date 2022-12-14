package com.ordinaka.goodreads.controllers;

import com.ordinaka.goodreads.controllers.requestsAndResponse.ApiResponse;
import com.ordinaka.goodreads.controllers.requestsAndResponse.AccountCreationRequest;
import com.ordinaka.goodreads.controllers.requestsAndResponse.UpdateRequest;
import com.ordinaka.goodreads.dtos.UserDto;
import com.ordinaka.goodreads.exceptions.GoodReadsException;
import com.ordinaka.goodreads.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/ap/v1/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){this.userService=userService;}

    public ResponseEntity<?> createUser(@RequestBody @Valid @NotNull AccountCreationRequest accountCreationRequest){
     try{
         log.info("Account Creation Request ==> {}", accountCreationRequest);
         UserDto userDto = userService.createUserAccount(accountCreationRequest);
         ApiResponse apiResponse = ApiResponse.builder()
                 .status("success")
                 .message("user created success")
                 .data(userDto)
                 .build();
         log.info("returning response");
         return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
     }
     catch (GoodReadsException e){
         ApiResponse apiResponse = ApiResponse.builder()
                 .status("fail")
                 .message(e.getMessage())
                 .build();
         return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
     }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") @NotNull @NotBlank String userId){
        try{
            if(("null").equals(userId) || ("").equals(userId.trim())){
                throw new GoodReadsException("String id cannot be null");
            }
            UserDto userDto = userService.findUserById(userId);
            ApiResponse apiResponse = ApiResponse.builder()
                    .status("success")
                    .message("user found")
                    .data(userDto)
                    .result(1)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (GoodReadsException e){
            ApiResponse apiResponse =ApiResponse.builder()
                    .status("fail")
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(e.getStatusCode()));
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(){
        List<UserDto> users = userService.findAll();
        ApiResponse apiResponse = ApiResponse.builder()
                .status("success")
                .message(users.size() != 0 ? "users found" : "no user exists in database")
                .data(users)
                .result(users.size())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PatchMapping("/")
    public ResponseEntity<?> updateUserProfile(@Valid @NotBlank @NotNull @RequestParam String id,
                                               @RequestBody @NotNull UpdateRequest updateRequest ) {

        try {
            UserDto userDto = userService.updateUserProfile(id, updateRequest);
            ApiResponse apiResponse = ApiResponse.builder()
                    .status("success")
                    .message("user found")
                    .data(userDto)
                    .result(1)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } catch (GoodReadsException e) {
            ApiResponse apiResponse = ApiResponse.builder()
                    .status("fail")
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(e.getStatusCode()));
        }
    }
}
