package com.vano.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String order_id;
    private long products_id;
    private int quantity;
    private double price;
    private String order_on;
    private int status;    // status of the order


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public long getProducts_id() {
        return products_id;
    }

    public void setProducts_id(long products_id) {
        this.products_id = products_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrder_on() {
        return order_on;
    }

    public void setOrder_on(String order_on) {
        this.order_on = order_on;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
