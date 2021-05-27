package com.example.simplecaffemanagerassignment.repositories;

import com.example.simplecaffemanagerassignment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
