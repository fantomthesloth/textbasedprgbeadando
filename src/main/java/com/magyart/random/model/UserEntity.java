package com.magyart.random.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * Class that generates the USER table.
 */
@Slf4j
@Entity
@Table(name="USERS")
@Data
public class UserEntity {

    /**
     * Primary key of the entity, automatically generated.
     */
    @Id
    @GeneratedValue
    @Column(name="ID", nullable = false, updatable = false)
    private Long id;

    /**
     * Users's username.
     */
    @Column(name = "USERNAME")
    private String username;

    /**
     * User's password.
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * Column to join this table with {@link PlayerEntity}'s table.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="PLAYER")
    private PlayerEntity playerEntity;

}
