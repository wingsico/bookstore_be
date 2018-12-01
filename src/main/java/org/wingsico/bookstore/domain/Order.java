package org.wingsico.bookstore.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 订单实体类
 *
 */
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "orders")
@Data
public class Order implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int orderID;

    @Column(nullable = false)
    private int userID;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private Timestamp date;

    @Column(nullable = false)
    private int bookID;
}
