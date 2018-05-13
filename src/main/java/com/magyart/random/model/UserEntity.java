package com.magyart.random.model;

/*-
 * #%L
 * Random
 * %%
 * Copyright (C) 2018 University of Debrecen
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
