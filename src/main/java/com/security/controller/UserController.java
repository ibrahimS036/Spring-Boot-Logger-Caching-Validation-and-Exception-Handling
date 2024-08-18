package com.security.controller;

import com.security.dto.UserDto;
import com.security.modles.User;
import com.security.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userservice;

    //Insert Data
    @PostMapping("/insertData")
    public ResponseEntity<UserDto> insert(@Valid @RequestBody UserDto user) {
        UserDto userDto = this.userservice.insertData(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    //Fetch  All Data
    @GetMapping("/fetchAll")
    public ResponseEntity<List<UserDto>> fetch() {
        List<UserDto> fetched = this.userservice.fetchAll();
        return new ResponseEntity<>(fetched, HttpStatus.OK);
    }

    //Fetch By id
    @GetMapping("/findById/{findId}")
    public ResponseEntity<UserDto> ById(@PathVariable int findId) {
        UserDto byId = this.userservice.FetchById(findId);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    //Update user
    @PutMapping("updateUser/{updateId}")
    public ResponseEntity<UserDto> update(@RequestBody User user, @PathVariable int updateId) {
        UserDto update = this.userservice.updateUser(user, updateId);
        return new ResponseEntity<>(update, HttpStatus.CREATED);
    }

    //Delete User
    @DeleteMapping("deleteUser/{deleteId}")
    public ResponseEntity<List<UserDto>> delete(@PathVariable int deleteId) {
        List<UserDto> deleted = this.userservice.deleteUserById(deleteId);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    //Search Api
    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> findByUsernName(@RequestParam ("username") String username) {
        List<UserDto> byUsername = this.userservice.findByUsername(username);
        return new ResponseEntity<>(byUsername, HttpStatus.OK);
    }


}
