package com.example.simplecaffemanagerassignment.repositories;

import com.example.simplecaffemanagerassignment.model.Admin;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    @NotNull
    @Override
    Admin getById(@NotNull Long aLong);

    Admin getByLogin(String login);
}
