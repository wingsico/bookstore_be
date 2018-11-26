package org.wingsico.bookstore.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User实体类
 *
 */
@Entity
@Table(name = "users")
@Data
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    @Column(nullable = false)
    private String user;

    @Column(nullable = false)
    private String password;


}
