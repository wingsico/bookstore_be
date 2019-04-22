package org.wingsico.bookstore.service;

import org.wingsico.bookstore.domain.Business;

public interface BusinessService {
    /**
     * 获取书籍商户
     * @param bookId
     * @return
     */
    Business getBusiness(int bookId);

    /**
     * 添加商户对应书籍信息
     * @param userId
     * @param bookId
     */
    void setBusiness(int userId, int bookId);
}
