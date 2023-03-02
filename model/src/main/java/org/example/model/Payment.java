package org.example.model;

public class Payment{
    private int orderNumber;
    private double amount;
    private String paymentType;

    public Payment(int orderNumber, double amount, String paymentType){
        this.orderNumber = orderNumber;
        this.amount = amount;
        this.paymentType = paymentType;
    }

    public int getOrderNumber(){
        return orderNumber;
    }

    public double getAmount(){
        return amount;
    }

    public String getPaymentType(){
        return paymentType;
    }

    public void processPayment(){
        //logic to process payment
    }
}