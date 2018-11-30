package org.wingsico.bookstore.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.wingsico.bookstore.primarykey.OrderCommodity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 订单中的商品实体类
 *
 */
@Entity
@Table(name = "commodities")
@Data
@IdClass(OrderCommodity.class)
public class Commodity implements Serializable {
    @Id
    @Column(nullable = false)
    private int commodityID;

    @Id
    @Column(nullable = false)
    private int orderID;

    @Column(nullable = false)
    private String bookTitle;

    @Column(nullable = false)
    private float bookPrice;

    @Column(nullable = false)
    int number;
}
