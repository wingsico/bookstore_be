package org.wingsico.bookstore.domain;

import java.sql.Timestamp;
import java.util.ArrayList;

public class OrderBooks {
    private ArrayList<Integer> bookIDs;

    private int orderID;

    private int userID;

    private int status;

    private Timestamp date;

    public ArrayList<Integer> getBookIDs() {
        return bookIDs;
    }

    public void setBookIDs(ArrayList<Integer> bookIDs) {
        this.bookIDs = bookIDs;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
