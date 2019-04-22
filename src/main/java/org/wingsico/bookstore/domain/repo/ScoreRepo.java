package org.wingsico.bookstore.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wingsico.bookstore.domain.Score;

@Repository
public interface ScoreRepo extends JpaRepository<Score, Integer> {
    Score findByBookIdAndUserId(int bookId, int userId);
}
