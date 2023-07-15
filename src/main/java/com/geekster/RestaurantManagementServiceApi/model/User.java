package com.geekster.RestaurantManagementServiceApi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.geekster.RestaurantManagementServiceApi.model.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,scope=User.class,property="id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+$", message = "Please Enter Valid Email Address")
    @Column(nullable = false, unique = true)
    private String userEmail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;
}
