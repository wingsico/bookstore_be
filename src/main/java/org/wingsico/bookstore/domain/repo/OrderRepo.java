package org.wingsico.bookstore.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wingsico.bookstore.domain.Order;

import java.util.List;

/**
 * Order 数据持久层操作接口
 *
 */
@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    List<Order> findAllByUserIDAndStatus(int userId, int status);
    List<Order> findAllByBookIDAndStatus(int bookId, int status);
}
