package org.example.model;

import java.util.List;

public class Order{
    private int orderNumber;
    private String orderStatus;
    private double totalCost;
    private List<Product> products;

    public Order(int orderNumber, List<Product> products){
        this.orderNumber = orderNumber;
        this.products = products;
    }

    public int getOrderNumber(){
        return orderNumber;
    }

    public void setOrderStatus(String orderStatus){
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus(){
        return orderStatus;
    }

    public double getTotalCost(){
        return totalCost;
    }

    public void calculateTotalCost(){
        totalCost = 0.0;
        for (Product p : products){
            totalCost += p.getPrice();
        }
    }

    @Override
    public String toString() {
        return "Order [orderNumber=" + orderNumber + ", orderStatus="
                + orderStatus + ", totalCost=" + totalCost + ", products="+ products.toString() + "]";
    }
}
