package com.example.simplecaffemanagerassignment.controllers.user;

import com.example.simplecaffemanagerassignment.Services.manager.ManagerService;
import com.example.simplecaffemanagerassignment.Services.waiter.WaiterService;
import com.example.simplecaffemanagerassignment.model.Product;
import com.example.simplecaffemanagerassignment.repositories.TableRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerService;
    final WaiterService waiterService;
    final TableRepository tableRepository;

    public ManagerController(ManagerService managerService, WaiterService waiterService, TableRepository tableRepository) {
        this.managerService = managerService;
        this.waiterService = waiterService;
        this.tableRepository = tableRepository;
    }

    @GetMapping("/register")
    public String managerInputCode(Model model) {
        return "redirect:/register";
    }

    @GetMapping("/tables")
    public String getTables(Model model) {
        model.addAttribute("tables",managerService.getAllTables());
        return "tables";
    }

    @PostMapping("/tables/add")
    public String createTable (Model model) {
        managerService.createNewTable();
        return "tables";
    }

    @GetMapping("/users")
    public String getUsers (Model model) {
        model.addAttribute("users",managerService.getAllUsers());
        return "users";
    }

    @PostMapping("/table/assign")
    public String assignTableToWaiter(@RequestParam(name ="tableID") Long tableId, @RequestParam(name = "waiterId") long waiterId) {
        managerService.assignTableToWaiter(tableId,waiterId);
        return "/";
    }

    @GetMapping("/products")
    public String getProducts (Model model) {
        List<Product>products =  managerService.getAllProducts();
        model.addAttribute("products",products);
        return "products";
    }

    @GetMapping("/products/add")
    public String productAddForm(Model model) {
        return "addProductForm";
    }

    @PostMapping("/products/add")
    public String addProduct(@RequestParam(name = "productname") String productName,Model model) {
        managerService.createNewProduct(productName);
        return "/";
    }
}
