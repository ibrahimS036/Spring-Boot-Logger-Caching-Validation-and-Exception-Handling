package com.security.services;

import com.security.dto.UserDto;
import com.security.modles.User;
import com.security.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userrepository;

    @Autowired
    private ModelMapper modlemapper;

    //Insert Data
    public UserDto insertData(UserDto user) {
        logger.info("Inserted Data: {}", user);
        User insert = modlemapper.map(user, User.class);
        User saveData = this.userrepository.save(insert);
        UserDto map = modlemapper.map(saveData, UserDto.class);
        return map;
    }

    //Fetch  All Data
    //Core Java logic
    @Cacheable(cacheNames = "api")
    public List<UserDto> fetchAll() {
        List<User> all = this.userrepository.findAll();
        List<UserDto> dto = new ArrayList<>();
        for (User us : all) {
            UserDto userDto = new UserDto();
            userDto.setId(us.getId());
            userDto.setUsername(us.getUsername());
            userDto.setEmail(us.getEmail());
            userDto.setPassword(us.getPassword());
            dto.add(userDto);
        }
        logger.info("Data Fetched Successfully...!");
        return dto;

        //Java 8 logic
//       List<UserDto> list = all.stream().map(e -> this.modlemapper.map(e,UserDto.class)).toList();
//       logger.info("Data Fetched");
//       return list;
    }


    //Fetch By id
    @Cacheable(cacheNames = "api", key = "#id")
    public UserDto FetchById(int id) {
//        Optional<User> byId = this.userrepository.findById(id);
//        if (byId.isEmpty()) {
//            logger.warn("id not found");
//            throw new RuntimeException("User not found");
//        } else {
//            return this.modlemapper.map(byId.get(), UserDto.class);
//        }

        //Java 8 logic
        User userNotFound = this.userrepository.findById(id).orElseThrow(() -> {
            logger.error("User with ID: {} not found", id);
            return new RuntimeException("User not found");
        });
        UserDto map = this.modlemapper.map(userNotFound, UserDto.class);
        return map;
    }

    //Update User
    //Core Java logic
    @CachePut(cacheNames = "api", key = "#id")
    public UserDto updateUser(User user, int id) {
        UserDto userDto = this.FetchById(id);
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        UserDto userDto1 = this.insertData(userDto);
        logger.info("User with ID: {} Updated Successfully...!", id);
        return userDto1;
    }

    //Delete User
    @CacheEvict(cacheNames = "api", key = "#deleteId")
    public List<UserDto> deleteUserById(int deleteId) {
        this.userrepository.deleteById(deleteId);
        logger.info("User with ID: {} Deleted Successfully...!", deleteId);
        return fetchAll();
    }

    //Search Api
    //Core Java logic
    public List<UserDto> findByUsername(String username) {
        List<User> byUsernameContaining = this.userrepository.findByUsernameContaining(username);
        List<UserDto> list = new ArrayList<>();
        for (User ref : byUsernameContaining) {
            UserDto obj = new UserDto();
            obj.setId(ref.getId());
            obj.setUsername(ref.getUsername());
            obj.setPassword(ref.getPassword());
            obj.setEmail(ref.getEmail());
            list.add(obj);
        }
        return list;
    }


}
