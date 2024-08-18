package com.vikram.code.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Users")
public class User {

    @Id
    @Column(name="Id")
    private String userId;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="about")
    private String about;


}
