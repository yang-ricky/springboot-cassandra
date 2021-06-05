package com.bilaldemir.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bilaldemir.model.User;

public interface UserRepository extends CrudRepository<User, String> {

    @Query("select * from user")
    List<User> findByName(String name);

}
