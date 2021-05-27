package com.example.simplecaffemanagerassignment.repositories;

import com.example.simplecaffemanagerassignment.model.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder,Long> {
}
