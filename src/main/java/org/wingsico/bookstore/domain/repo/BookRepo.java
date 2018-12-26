package org.wingsico.bookstore.domain.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.wingsico.bookstore.domain.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Book 数据持久层操作接口
 *
 */
@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
//    @Query(value = "SELECT * FROM BOOKS WHERE CLASSIFICATION = ?1",
//    countQuery = "SELECT count(*) FROM BOOKS WHERE CLASSIFICATION = ?1",
//    nativeQuery = true)
    @Query
    Page<Book> findByClassification(int classification, Pageable pageable);

    @Query
    List<Book> findByClassification(int classification);

    @Query("SELECT b FROM Book b WHERE b.content LIKE CONCAT('%',:content,'%')")
    List<Book> findByContent(@Param("content") String content);
}
