package com.example.simplecaffemanagerassignment.Services.manager;

import com.example.simplecaffemanagerassignment.model.*;

import java.util.List;

public interface ManagerService {
    Manager getOneByLogin(String login);

    Manager addManager(Manager manager);

    void createNewTable();

    void assignTableToWaiter(Long tableId, Long waiterId);

    Product createNewProduct(String newProductName);

    Manager createNewManager(User user);

    List<Table> getAllTables();

    List<User> getAllUsers();

    List<Product> getAllProducts();
}
