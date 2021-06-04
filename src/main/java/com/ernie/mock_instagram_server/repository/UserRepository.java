package com.ernie.mock_instagram_server.repository;

import com.ernie.mock_instagram_server.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> getUserByUsername(String username);
}
