package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Row;

@Component
public class CassandraQueryExecutor {

    @Autowired
    private CassandraOperations cassandraTemplate;

    public List<Row> getTable(String tableName) throws Exception {
        List<Row> result = null;
        try {
            result = cassandraTemplate.select("SELECT * FROM " + tableName, Row.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return result;
    }

    public List<Row> runQuery(String query) throws Exception {

        List<Row> result = null;
        try {
            result = cassandraTemplate.select(query, Row.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return result;
    }
}
