package org.wingsico.bookstore.service;

import org.wingsico.bookstore.domain.BookAveScore;
import org.wingsico.bookstore.domain.Score;

import java.util.List;

public interface ScoreService {
    /**
     * 获取某本书的评分
     *
     */
    BookAveScore getScore(int bookId);

    /**
     * 用户为某本书进行评分
     *
     */
    void setScore(int bookId, int uerId, double point);

    /**
     * 判断该用户是否已经评价某书
     *
     */
    boolean isScore(int bookId, int userId);
}
