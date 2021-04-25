package com.project.wchallenge.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 8799656478674716631L;

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @Column(nullable = false)
    private Address address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String website;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @Column(nullable = false)
    private Company company;

}
