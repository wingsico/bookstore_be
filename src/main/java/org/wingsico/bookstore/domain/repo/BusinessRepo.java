package org.wingsico.bookstore.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wingsico.bookstore.domain.Business;

@Repository
public interface BusinessRepo extends JpaRepository<Business, Integer> {
    Business findByBookId(int bookId);
}
