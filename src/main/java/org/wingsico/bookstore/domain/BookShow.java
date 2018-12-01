package org.wingsico.bookstore.domain;

import java.util.List;

public class BookShow {
    private int nowPage;
    private int pageSize;
    private int totalPage;
    private Object books;

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Object getBooks() {
        return books;
    }

    public void setBooks(Object books) {
        this.books = books;
    }
}
