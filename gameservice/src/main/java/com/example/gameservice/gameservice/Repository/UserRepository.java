package com.example.gameservice.gameservice.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.gameservice.gameservice.Model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
