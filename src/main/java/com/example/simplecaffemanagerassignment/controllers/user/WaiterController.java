package com.example.simplecaffemanagerassignment.controllers.user;

import com.example.simplecaffemanagerassignment.Services.waiter.WaiterService;
import com.example.simplecaffemanagerassignment.model.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/waiter")
public class WaiterController {
    final WaiterService waiterService;

    public WaiterController(WaiterService waiterService) {
        this.waiterService = waiterService;
    }

    @GetMapping("/tables")
    public String tables(Model model) {
        Waiter waiter = (Waiter) SecurityContextHolder.getContext().getAuthentication();
        List<Table> tables = waiterService.getTablesAssignedToWaiter(waiter);
        model.addAttribute("tables", tables);
        return "/tables";
    }

    @PostMapping("/add/order")
    public String addOrderToTable(Model model, Map<Integer, Product> productsWithCounts, @RequestParam(name = "tableId") Long tableId) {
        Boolean isOrderAdded = waiterService.createOrderForTable(productsWithCounts, tableId);
        model.addAttribute("isOrderAdded", isOrderAdded);
        return "/";
    }

    @PostMapping("order/add")
    public String addProductInOrder(Model model, @RequestParam Long orderId) {
        Boolean isProductsAddedInOrder = waiterService.addNewOrderProductsInOrder(orderId);
        model.addAttribute("isProductsAddedInOrder", isProductsAddedInOrder);
        return "/productsinorder";
    }

    @PostMapping("productinrder/cancel")
    public String cancelProductInOrder(Model model, @RequestParam Long productInOrderId) {
        waiterService.cancelProductInOrder(productInOrderId);
        model.addAttribute(productInOrderId);
        return "/producitsinorder";
    }

}
