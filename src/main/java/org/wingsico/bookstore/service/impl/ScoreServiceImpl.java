package org.wingsico.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingsico.bookstore.domain.BookAveScore;
import org.wingsico.bookstore.domain.Score;
import org.wingsico.bookstore.domain.repo.BookAveScoreRepo;
import org.wingsico.bookstore.domain.repo.ScoreRepo;
import org.wingsico.bookstore.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    ScoreRepo scoreRepo;

    @Autowired
    BookAveScoreRepo bookAveScoreRepo;

    @Override
    public BookAveScore getScore(int bookId){
        return bookAveScoreRepo.findByBookId(bookId);
    }

    @Override
    public void setScore(int bookId, int uerId, double point){
        Score score = new Score();
        score.setBookId(bookId);
        score.setUserId(uerId);
        score.setBookPoint(point);
        scoreRepo.save(score);
        BookAveScore bookAveScore = getScore(bookId);
        if (bookAveScore == null){
            bookAveScore = new BookAveScore();
            bookAveScore.setBookId(bookId);
            bookAveScore.setNumber(1);
            bookAveScore.setPoints(point);
        }else {
            bookAveScore.setPoints((bookAveScore.getPoints()*bookAveScore.getNumber()+point)/(bookAveScore.getNumber()+1));
            bookAveScore.setNumber(bookAveScore.getNumber()+1);
        }
        bookAveScoreRepo.save(bookAveScore);
    }

    @Override
    public boolean isScore(int bookId, int userId){
        Score score = scoreRepo.findByBookIdAndUserId(bookId, userId);
        if (score == null){
            return false;
        }
        return true;
    }
}
