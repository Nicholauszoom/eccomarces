package com.example.eccomarce.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Column;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.List;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false,  unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
