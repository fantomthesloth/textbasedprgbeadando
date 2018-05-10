package com.magyart.random.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Entity
@Table(name="USERS")
@Data
@EqualsAndHashCode
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name="ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="PLAYER")
    private PlayerEntity playerEntity;

}
