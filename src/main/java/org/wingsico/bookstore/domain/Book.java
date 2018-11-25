package org.wingsico.bookstore.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Book 实体类
 *
 */
@Entity
@Table(name="books")
@Data
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    @Column(nullable = false)
    private int classification;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String content;



    @Column(nullable = false)
    private String cover_url;

    @Column(nullable = false)
    private Timestamp publish_date;

    @Column(nullable = false)
    private String author_intro;

    @Column(nullable = false)
    private Float price;
}
