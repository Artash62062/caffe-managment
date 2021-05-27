package com.example.simplecaffemanagerassignment.repositories;

import com.example.simplecaffemanagerassignment.model.Order;
import com.example.simplecaffemanagerassignment.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Override
    Order getById(Long id);
    List<Order> getAllByTable(Table table);

}
