package com.example.model;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.example.Utils.Constans;

@Table(value = Constans.USER_TABLE)
public class User {

    @Column(Constans.USER_TABLE_ID)
    @PrimaryKey
    private String id;

    @Column(Constans.USER_TABLE_NAME)
    private String name;

    @Column(Constans.USER_TABLE_LASTNAME)
    private String lastName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + "]";
    }

}
