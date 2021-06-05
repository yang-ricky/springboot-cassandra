package com.example.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controller.UserController;
import com.example.model.User;
import com.example.repository.CassandraQueryExecutor;
import com.example.repository.UserRepository;
import com.datastax.driver.core.Row;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository              userRespository;

    @Autowired
    CassandraQueryExecutor      descRespo;

    public User savaUser(User user) {
        logger.debug("[saveUser] : save user request called. user -> {}", user);
        User response = userRespository.save(user);
        logger.debug("[saveUser] : save user request finished successfuly. user -> {}", response);
        return response;
    }

    public List<User> getAll() {
        logger.debug("[getAll] : get all user request called.");
        Iterable<User> listUser = userRespository.findAll();

        List<User> response = StreamSupport.stream(listUser.spliterator(), false).collect(Collectors.toList());
        System.out.println("ricky" + response.size());
        logger.debug("[getAll] : get all user request finished successfuly. response -> {}", response);
        return response;
    }

    public String runQuery(String query) throws Exception {
        logger.debug("[runQuery] : run query request called. query -> {}", query);
        List<Row> result = descRespo.runQuery(query);
        String firstLine = " |   query : " + query + "   |";
        String responseString = formatRowResultToString(result, firstLine);
        logger.debug("[runQuery] : run query request finished successfuly. responseString -> {}", responseString);
        return responseString;
    }

    public String getTable(String tableName) throws Exception {
        logger.debug("[getTable] : getTable values request called. tableName -> {}", tableName);
        List<Row> result = descRespo.getTable(tableName);
        String firstLine = " |   table name : " + tableName + "   |";

        String responseString = formatRowResultToString(result, firstLine);
        logger.debug("[getTable] : getTable values request finished successfuly. responseString -> {}", responseString);
        return responseString;
    }

    public List<User> getUserByName(String name) {
        logger.debug("[getUserByName] : get User By Name request called. name -> {}", name);
        List<User> response = userRespository.findByName(name);
        logger.debug("[getUserByName] : get User By Name request finished successfuly. user -> {}", response);
        return response;
    }

    private String formatRowResultToString(List<Row> response, String firstLine) {
        StringBuffer stringBuffer = new StringBuffer(firstLine);
        int length = stringBuffer.length();
        stringBuffer.append("\n" + String.join("", Collections.nCopies(length, "-")) + "\n");
        stringBuffer.insert(0, String.join("", Collections.nCopies(length, "-")) + "\n");
        Row firstRow = response.get(0);

        firstRow.getColumnDefinitions().asList().forEach(columnName -> {
            stringBuffer.append(" | " + columnName.getName() + " | ");
        });
        stringBuffer.append("\n" + String.join("", Collections.nCopies(length, "-")) + "\n");
        response.forEach(row -> {
            row.getColumnDefinitions().asList().forEach(column -> {
                stringBuffer.append(" | " + row.get(column.getName(), String.class) + " | ");
            });
            stringBuffer.append("\n");
        });

        return stringBuffer.toString();
    }

    public User getUserById(String id) {
        logger.debug("[getUserById] : get User By id request called. id -> {}", id);
        User response = userRespository.findOne(id);
        logger.debug("[getUserById] : get User By id request finished successfuly. user -> {}", response);
        return response;
    }

    public void deleteUserById(String id) {
        logger.debug("[getUserById] : delete User By id request called. id -> {}", id);
        userRespository.delete(id);
        logger.debug("[getUserById] : delete User By id request finished successfuly.");
    }

    public User putUserById(User user) throws Exception {
        User oldUser = userRespository.findOne(user.getId());
        if(oldUser == null){
            throw new Exception("User Not found. userId -> ".concat(user.getId()));
        } else {
            userRespository.save(user);
        }
        return user;
    }

}
