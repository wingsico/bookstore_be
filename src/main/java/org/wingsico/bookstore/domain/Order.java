package org.wingsico.bookstore.domain;


import lombok.Data;
import org.wingsico.bookstore.primarykey.CartOrder;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 订单实体类
 *
 */
@Entity
@Table(name = "orders")
@Data
@IdClass(CartOrder.class)
public class Order implements Serializable{
    @Id
    @Column(nullable = false)
    private int cartID;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int orderID;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private Timestamp date;
}
