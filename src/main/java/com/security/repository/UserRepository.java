package com.security.repository;


import com.security.dto.UserDto;
import com.security.modles.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


   List<User> findByUsernameContaining(String username);

}


