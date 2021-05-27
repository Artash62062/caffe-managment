package com.example.simplecaffemanagerassignment.Services.waiter;

import com.example.simplecaffemanagerassignment.model.*;

import java.util.List;
import java.util.Map;

public interface WaiterService {

    Waiter createNewWaiter(User waiter);

    Waiter getOneByLogin(String login);
    Boolean addNewOrderProductsInOrder(Long orderId);

    List<Table> getTablesAssignedToWaiter(Waiter waiter);

    Boolean createOrderForTable(Map<Integer, Product> productsAndAmounts, Long tableID);



    Boolean cancelProductInOrder(Long productInOrderId);

    Boolean removeProductInOrder(ProductInOrder product);

    Boolean editOrder(Map<Integer, Product> productsAndAmounts, Order order);
}
