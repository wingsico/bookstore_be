package org.wingsico.bookstore.domain;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

public class BookInput {

    private int classification;

    private String title;

    private String author;

    private String content;

    private String publish_date;

    private String author_intro;

    private Float price;

    private String press;

    private MultipartFile cover_url;

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public MultipartFile getCover_url() {
        return cover_url;
    }

    public void setCover_url(MultipartFile cover_url) {
        this.cover_url = cover_url;
    }
}
