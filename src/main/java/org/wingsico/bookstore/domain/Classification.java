package org.wingsico.bookstore.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Classification实体类
 *
 */
@Entity
@Table(name="classification")
@Data
public class Classification implements Serializable{
    @Id
    @Column(unique = true)
    private int id;

    @Column(nullable = false)
    private String name;
}
