package org.wingsico.bookstore.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 购物车实体类
 *
 */
@Entity
@Table(name = "shoppingcarts")
@Data
public class ShoppingCart implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    @Column(nullable = false)
    private User user;

    @Column
    private Book book;

    @Column
    private int number;
}
