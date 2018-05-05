package com.magyart.random.DB.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * JPA entitás, azaz az órán is tárgyalt POJO.
 *
 * Egy ilyen POJO szemléltet a JPA-ban egy Táblát. Minden egyes táblán
 * lévő oszlop, egy-egy java-beli változó, melyet a JPA @Column annotációjával
 * jelölünk és benne adjuk meg a táblabeli nevét, hogy nullable-e, max hosszát
 * is megadhatjuk, stb...
 *
 * Több tábla létrehozásához több osztályt kell létrehozni, és ezekben az
 * entitásokban a @OneToOne vagy @OneToMany annotációt használva kapcsolhatjuk
 * (join) össze őket.
 *
 * A NamedQuery-k (adatbázis lekérdezések) kifejtése a SampleDB-ben töténik.
 * Fontos, hogy tudd, mivel tér majd vissza a lekérdezés. A JPA
 * lekérdezések nagyon hasonlóak a tanult SQL-hez, azzal a különbséggel, hogy
 * itt mindig objektumokban kell gondolkozni. A Querykben lévő 'e' egy
 * adatbázisban lévő, már oda elmentett Entitás (objektum), melynek tagjaira ugyan úgy ponttal
 * lehet hivatkozni.
 *
 * plusz info Jeszy diáin
 *
 * @author balint
 */

@Entity
@Table(name = "USERS")
@NamedQueries({
        @NamedQuery(name = "SampleJPAEntity.findPlayerByID", query = "SELECT e FROM SampleEntity e WHERE e.id = :id ORDER BY e.score")
        ,
        @NamedQuery(name = "SampleJPAEntity.findPlayerByName", query = "SELECT e FROM SampleEntity e WHERE LOWER(e.playerName) LIKE LOWER(:playerName) ORDER BY e.score")
        ,
        @NamedQuery(name = "SampleJPAEntity.findHighScore", query = "SELECT MAX(e.score) FROM SampleEntity e")
})

@Data //fontos, JPA használja a Gettereket, Settereket
@EqualsAndHashCode  //mégfontosabb, hiszen a JPA ezeken keresztül tud összehasonlítani 2 entitást
@ToString(callSuper = true)

public class SampleEntity implements Serializable {

    /**
     * Entitás elsődleges, automatikusan generált kulcsa. (kötelező JPA elem)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)             // az itt zárójelben megadott 'strategy'-nek amúgy ez a default értéke is, csak szemléltetés miatt írtam ide,
    @Column(name = "ID", nullable = false, updatable = false)   // hiszen a JPA tud saját magától is egyedi ID-t generálni
    private Long id;

    /**
     * Játékos neve.
     */
    @Column(name = "PLAYER_NAME")
    private String playerName;

    /**
     * Játékoshoz tartozó elért pontszám.
     */
    @Column(name = "SCORE")
    private Integer score;

/*
    @OneToOne(cascade = CascadeType.ALL) // az entitáson végzett műveletek kihatnak mind2re
    @JoinColumn(name="tábla_2-idja") // tökm1 mi a neve
    private User user; // a jpa tudja hogy létezi 1 másik User entitás is*/
}