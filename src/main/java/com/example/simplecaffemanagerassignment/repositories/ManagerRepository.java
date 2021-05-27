package com.example.simplecaffemanagerassignment.repositories;

import com.example.simplecaffemanagerassignment.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository  extends JpaRepository<Manager,Long> {
  Manager getByLogin(String login);

}
