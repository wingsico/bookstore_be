package org.wingsico.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "bookAveScore")
@Data
public class BookAveScore implements Serializable{
    @Id
    @Column(unique = true)
    private int bookId;

    private int number;

    private double points;
}
