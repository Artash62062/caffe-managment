package com.example.simplecaffemanagerassignment.Services.manager;

import com.example.simplecaffemanagerassignment.model.*;
import com.example.simplecaffemanagerassignment.repositories.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
    final PasswordEncoder passwordEncoder;
    final ManagerRepository managerRepository;
    final TableRepository tableRepository;
    final ProductRepository productRepository;
    final WaiterRepository waiterRepository;
    final OrderRepository orderRepository;

    public ManagerServiceImpl(OrderRepository orderRepository, TableRepository tableRepository, ProductRepository productRepository, WaiterRepository waiterRepository, ManagerRepository managerRepository, PasswordEncoder passwordEncoder) {
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
        this.productRepository = productRepository;
        this.waiterRepository = waiterRepository;
        this.managerRepository = managerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Manager addManager(Manager manager) {
        managerRepository.save(manager);
        return manager;
    }

    public Manager getOneByLogin(String login) {
        return managerRepository.getByLogin(login);
    }

    @Override
    public void createNewTable() {
        Table newTable = new Table();
        tableRepository.save(newTable);
    }

    public List<User> getAllUsers() {
        List<Manager> managers = managerRepository.findAll();
        List<Waiter> waiters = waiterRepository.findAll();
        List<User> users = new LinkedList<>();
        users.addAll(managers);
        users.addAll(waiters);
        return users;
    }

    @Override
    public List<Table> getAllTables() {
        return tableRepository.findAll();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void assignTableToWaiter(Long tableId, Long waiterId) {
        Table table = tableRepository.getById(tableId);
        Waiter waiter = waiterRepository.getById(waiterId);
        table.setWaiter(waiter);
        tableRepository.save(table);
    }

    @Override
    public Product createNewProduct(String newProductName) {
        Product newProduct = new Product();
        newProduct.setName(newProductName);
        productRepository.save(newProduct);
        return newProduct;
    }

    @Override
    public Manager createNewManager(User user) {
        user.setRole("ROLE_MANAGER");
        Manager newManager = userToManager(user);
        managerRepository.save(newManager);
        return newManager;
    }

    private Manager userToManager(User user) {
        Manager manager = new Manager();
        manager.setRole(user.getRole());
        manager.setName(user.getName());
        manager.setLogin(user.getLogin());
        manager.setPassword(user.getPassword());
        return manager;
    }
}
