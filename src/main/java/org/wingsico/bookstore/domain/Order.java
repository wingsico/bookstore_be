package org.wingsico.bookstore.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单实体类
 *
 */
@Entity
@Table(name = "orders")
@Data
public class Order implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    @Column(nullable = false)
    private int userID;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private int commodityID;
}
