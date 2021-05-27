package com.example.simplecaffemanagerassignment.model;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity(name = "orders")
public class Order extends AbstractBaseEntity {

    @Enumerated(EnumType.ORDINAL)
    private OrderState orderState;

    @OneToMany
    private Map<Integer, Product> products;

    private Integer productAmount;

    @ManyToOne
    private Table table;

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Product> products) {
        this.products = products;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
