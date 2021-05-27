package com.example.simplecaffemanagerassignment.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "products")
public class Product extends AbstractBaseEntity {

    private String name;
    @ManyToOne
    private Order order;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
