package org.wingsico.bookstore.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.wingsico.bookstore.domain.Commodity;

import java.util.List;

/**
 * Commodity 数据持久层操作接口
 *
 */
@Repository
public interface CommodityRepo extends JpaRepository<Commodity, Integer> {
    List<Commodity> findAllByUserID(int userID);
}
