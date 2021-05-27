package com.example.simplecaffemanagerassignment.repositories;

import com.example.simplecaffemanagerassignment.model.Table;
import com.example.simplecaffemanagerassignment.model.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<Table,Long> {
    @Override
    Table getById(Long aLong);
    List<Table> getAllByWaiter(Waiter waiter);
}
