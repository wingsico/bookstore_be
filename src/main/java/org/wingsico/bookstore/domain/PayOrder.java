package org.wingsico.bookstore.domain;

import org.wingsico.bookstore.annotation.PaymentInformation;

public class PayOrder {
    private int orderID;
    @PaymentInformation
    private String payment;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
