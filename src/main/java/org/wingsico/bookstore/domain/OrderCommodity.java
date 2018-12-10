package org.wingsico.bookstore.domain;

import lombok.Data;
import org.wingsico.bookstore.primarykey.UserCommodity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 订单中的商品实体类
 *
 */
@Entity
@Table(name = "orderCommodities")
@Data
@IdClass(UserCommodity.class)
public class OrderCommodity implements Serializable {
    @Id
    @Column(nullable = false)
    private int bookID;

    @Id
    @Column(nullable = false)
    private int userID;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private int classification;

    @Column(nullable = false)
    private String cover_url;

    @Column(nullable = false)
    int number;
}
