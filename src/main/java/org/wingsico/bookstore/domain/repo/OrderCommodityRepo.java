package org.wingsico.bookstore.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wingsico.bookstore.domain.OrderCommodity;

/**
 * OrderCommodity 数据持久层操作接口
 *
 */
@Repository
public interface OrderCommodityRepo extends JpaRepository<OrderCommodity, Integer> {
}
