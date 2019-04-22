package org.wingsico.bookstore.service;

import org.wingsico.bookstore.domain.Book;
import org.wingsico.bookstore.domain.BookBrief;

import java.util.ArrayList;
import java.util.List;

public interface RecommendService {
    /**
     * 编辑推荐
     * @param userId
     * @return
     */
    void recommend(int userId);

    /**
     * 是否存在推荐
     * @param userId
     * @return
     */
    boolean isExist(int userId);

    /**
     * 获取推荐
     * @param userId
     * @return
     */
    ArrayList<Book> getRecommend(int userId);
}
