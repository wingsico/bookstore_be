package org.wingsico.bookstore.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wingsico.bookstore.domain.Recommend;

import java.util.List;

@Repository
public interface RecommendRepo extends JpaRepository<Recommend, Integer> {
    List<Recommend> findAllByUserId(int userId);
}
