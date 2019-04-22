package org.wingsico.bookstore.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.wingsico.bookstore.common.JsonResult;
import org.wingsico.bookstore.domain.*;
import org.wingsico.bookstore.domain.repo.BookRepo;
import org.wingsico.bookstore.domain.repo.RecommendRepo;
import org.wingsico.bookstore.service.BookService;
import org.wingsico.bookstore.service.OrderService;
import org.wingsico.bookstore.service.RecommendService;
import org.wingsico.bookstore.service.ScoreService;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Service
public class RecommendServiceImpl implements RecommendService{

    @Resource
    private RecommendRepo recommendRepo;

    @Resource
    private BookRepo bookRepo;

    @Resource
    private OrderService orderService;

    @Resource
    private BookService bookService;

    @Resource
    private ScoreService scoreService;

    private JsonResult jsonResult = new JsonResult();

    @Override
    @Async
    public void recommend(int userId){
        for (Recommend recommend:recommendRepo.findAllByUserId(userId)){
            recommendRepo.delete(recommend);
        }
        ArrayList<Recommend> recommends = new ArrayList<>();
        ArrayList<Book> classification = new ArrayList<>();
        ArrayList<Book> author = new ArrayList<>();
        ArrayList<Book> publish = new ArrayList<>();
        ArrayList<Book> otherUser = new ArrayList<>();
        ArrayList<Integer> users = new ArrayList<>();
        for (Order order:orderService.getUserOrders(userId, 2)){
            for (Order orderOther:orderService.getOrderByBookId(order.getBookID(), 2)){
                if (order.getUserID()!=orderOther.getUserID()){
                    if (!users.contains(orderOther.getUserID())){
                        users.add(orderOther.getUserID());
                    }
                }
            }
        }
        for (Integer user:users){
            for (Order order:orderService.getUserOrders(user, 2)){
                for (Order order1:orderService.getUserOrders(userId, 2)) {
                    if(order.getBookID()!=order1.getBookID()){
                        otherUser.add(bookService.findOne(order.getBookID()));
                    }
                }
            }
        }
        for (Book bookAll:bookService.findAllBooks()){
            Random random = new Random();
            int n = random.nextInt(3) + 3;
            if (bookAll.getId()%n==0){
                double point = 0;
                for (Order order:orderService.getUserOrders(userId, 2)){
                    Book book = bookService.findOne(order.getBookID());
                    if (bookAll.getId()!=book.getId()){
                        if (book.getClassification()==bookAll.getClassification()){
                            if (!classification.contains(bookAll)){
                                point += 10;
                                classification.add(bookAll);
                            }
                        }
                        if (book.getPress().equals(bookAll.getPress())){
                            if (!publish.contains(bookAll)) {
                                point += 10;
                                publish.add(bookAll);
                            }
                        }
                        if (book.getAuthor().equals(bookAll.getAuthor())){
                            if (!author.contains(bookAll)) {
                                point += 20;
                                author.add(bookAll);
                            }
                        }
                    }
                }
                if (scoreService.getScore(bookAll.getId())!=null){
                    point += scoreService.getScore(bookAll.getId()).getPoints() * 2;
                }
                if (otherUser.contains(bookAll)){
                    point += 40;
                }
                Recommend recommend = new Recommend();
                recommend.setBookId(bookAll.getId());
                recommend.setUserId(userId);
                recommend.setPoint(point);
                if (recommends.size()>=20){
                    Collections.sort(recommends, new Comparator<Recommend>() {
                        @Override
                        public int compare(Recommend o1, Recommend o2) {
                            return new Double((o2.getPoint()-o1.getPoint())*1000).intValue();
                        }
                    });
                    if (recommends.get(recommends.size()-1).getPoint()<point){
                        recommends.remove(recommends.get(recommends.size()-1));
                        recommends.add(recommend);
                    }
                }else {
                    recommends.add(recommend);
                }
            }
        }
        for (int i=0;i<recommends.size();i++){
            recommendRepo.save(recommends.get(i));
        }
    }

    @Override
    public boolean isExist(int userId){
        List<Recommend> recommends = recommendRepo.findAllByUserId(userId);
        if (recommends.size()==0){
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Book> getRecommend(int userId){
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Integer> select = new ArrayList<>();
        if (isExist(userId)){
            List<Recommend> recommends = recommendRepo.findAllByUserId(userId);
            for (Recommend recommend:recommends){
                Book book = bookService.findOne(recommend.getBookId());
                books.add(book);
            }
        }
        else {
            Random random = new Random();
            int m = bookService.getNumber();
            while (select.size()<20){
                int a = random.nextInt(m);
                if (!select.contains(a)){
                    select.add(a);
                }
            }
            for (int i=0;i<select.size();i++){
                Book book = bookService.findOne(select.get(i));
                books.add(book);
            }
        }
        return books;
    }
}
