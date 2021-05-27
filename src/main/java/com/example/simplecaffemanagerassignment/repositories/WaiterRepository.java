package com.example.simplecaffemanagerassignment.repositories;

import com.example.simplecaffemanagerassignment.model.Table;
import com.example.simplecaffemanagerassignment.model.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaiterRepository extends JpaRepository<Waiter,Long> {
    Waiter getWaiterByLogin(String Login);
    @Override
    Waiter getById(Long aLong);
    Waiter getWaiterByTablesContaining(Table table);
}
