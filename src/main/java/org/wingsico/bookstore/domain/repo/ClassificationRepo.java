package org.wingsico.bookstore.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wingsico.bookstore.domain.Classification;

/**
 * Classficition持久层接口
 *
 */
@Repository
public interface ClassificationRepo extends JpaRepository<Classification, Integer> {
}
