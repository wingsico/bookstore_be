package org.wingsico.bookstore.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wingsico.bookstore.domain.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
}