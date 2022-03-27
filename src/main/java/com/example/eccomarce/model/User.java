package com.example.eccomarce.model;


import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Column;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Data
@Entity
@Table(name ="\"users\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;

    @Column(nullable = false)
    @NotEmpty
    private String firstName;

    private String lastName;

    private String contact;

    private String location;

    @Column(nullable = false,unique = true)
    @NotEmpty
    @Email(message = "{errors.invalid_email}")
    private String email;


    private String password;

    @ManyToMany  (cascade =  CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
               joinColumns = {@JoinColumn(name ="USER_ID", referencedColumnName="ID")},
                inverseJoinColumns =  {@JoinColumn(name="ROLE_ID",referencedColumnName = "ID")}
    )
    private List<Role> roles;

    public User(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.contact = user.getContact();
        this.location = user.getLocation();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }

    public User() {

    }


}
