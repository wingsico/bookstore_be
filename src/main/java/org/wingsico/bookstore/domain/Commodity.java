package org.wingsico.bookstore.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 订单中的商品实体类
 *
 */
@Entity
@Table(name = "commodities")
@Data
public class Commodity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    @Column(nullable = false)
    private int orderID;

    @Column(nullable = false)
    private int bookID;

    @Column(nullable = false)
    private String bookTitle;

    @Column(nullable = false)
    private float bookPrice;

    @Column(nullable = false)
    int number;
}
