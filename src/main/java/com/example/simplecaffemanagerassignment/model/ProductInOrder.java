package com.example.simplecaffemanagerassignment.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Entity
public class ProductInOrder extends AbstractBaseEntity {
    @OneToOne
    private Product product;
    @OneToOne
    private Order order;

    private ProductInOrderState productInOrderState;

    private Integer amount;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ProductInOrderState getProductInOrderState() {
        return productInOrderState;
    }

    public void setProductInOrderState(ProductInOrderState productInOrderState) {
        this.productInOrderState = productInOrderState;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
