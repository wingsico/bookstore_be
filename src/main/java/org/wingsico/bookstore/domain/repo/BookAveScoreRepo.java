package org.wingsico.bookstore.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wingsico.bookstore.domain.BookAveScore;

import java.util.List;

@Repository
public interface BookAveScoreRepo extends JpaRepository<BookAveScore, Integer> {
    BookAveScore findByBookId(int bookId);
}
