package com.bilaldemir.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bilaldemir.model.User;
import com.bilaldemir.service.UserService;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService                 testService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        logger.debug("[saveUser] : save user request handled. user -> {}", user);
        user.setId(UUID.randomUUID().toString());
        User responseUser = testService.savaUser(user);
        logger.debug("[saveUser] : save user request finished successfuly. user -> {}", responseUser);
        return responseUser;
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        logger.debug("[getAllUser] : get all user request handled.");
        List<User> response = testService.getAll();
        logger.debug("[getAllUser] : get all user request finished successfuly. response -> {}", response);
        return response;

    }

    @GetMapping("/user/name/{name}")
    public List<User> getByName(@PathVariable String name) {
        logger.debug("[getByName] : get all user request handled.");
        List<User> response = testService.getUserByName(name);
        logger.debug("[getByName] : save user request finished successfuly. response -> {}", response);
        return response;

    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable String id) {
        logger.debug("[getByName] : get all user request handled.");
        User response = testService.getUserById(id);
        logger.debug("[getByName] : save user request finished successfuly. response -> {}", response);
        return response;

    }

    @DeleteMapping("/user/{id}")
    @ResponseBody()
    public void deleteUserById(@PathVariable String id) {
        logger.debug("[getByName] : get all user request handled.");
        testService.deleteUserById(id);
        logger.debug("[getByName] : save user request finished successfuly.");
    }

    @PutMapping("/user/{id}")
    public User putUserById(@RequestBody User user) throws Exception {
        logger.debug("[getByName] : get all user request handled.");
        User response = testService.putUserById(user);
        logger.debug("[getByName] : save user request finished successfuly. response -> {}", response);
        return response;
    }

    @PostMapping(value = "/query", produces = "text/plain")
    public String runCQLQuery(@RequestBody String query) throws Exception {
        logger.debug("[runCQLQuery] : Run CQL Query request handled. query -> {}", query);
        return testService.runQuery(query);
    }

    @GetMapping(value = "/table/{tableName}", produces = "text/plain")
    public String getTableData(@PathVariable String tableName) throws Exception {
        logger.debug("[getTableData] : Get table data request handled.");
        return testService.getTable(tableName);

    }

}
