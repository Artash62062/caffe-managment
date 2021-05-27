package com.example.simplecaffemanagerassignment.Services.waiter;

import com.example.simplecaffemanagerassignment.model.*;
import com.example.simplecaffemanagerassignment.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WaiterServiceImplantation implements WaiterService {
    final TableRepository tableRepository;
    final ProductRepository productRepository;
    final WaiterRepository waiterRepository;
    final OrderRepository orderRepository;
    final ProductInOrderRepository productInOrderRepository;

    public WaiterServiceImplantation(OrderRepository orderRepository, TableRepository tableRepository, ProductRepository productRepository, WaiterRepository waiterRepository, ProductInOrderRepository productInOrderRepository) {
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
        this.productRepository = productRepository;
        this.waiterRepository = waiterRepository;
        this.productInOrderRepository = productInOrderRepository;
    }

    @Override
    public Waiter createNewWaiter(User waiter) {
        waiter.setRole("ROLE_WAITER");
        Waiter newWaiter = userToWaiter(waiter);
        waiterRepository.save(newWaiter);
        return newWaiter;
    }

    @Override
    public Waiter getOneByLogin(String login) {
        return waiterRepository.getWaiterByLogin(login);
    }

    public Waiter getOneById(Long waiterId) {
        return waiterRepository.getById(waiterId);
    }

    @Override
    public List<Table> getTablesAssignedToWaiter(Waiter waiter) {
        return tableRepository.getAllByWaiter(waiter);
    }


    @Override
    public Boolean createOrderForTable(Map<Integer, Product> productsAndAmounts, Long tableID) {
        Table table = tableRepository.getById(tableID);
        List<Order>orders = orderRepository.getAllByTable(table);
        for (Order order:orders) {
            if(order.getOrderState().equals(OrderState.OPEN)) {
                return false;
            }
        }
        Order newOrder = new Order();
        newOrder.setOrderState(OrderState.OPEN);
        newOrder.setProducts(productsAndAmounts);
        newOrder.setTable(table);
        orderRepository.save(newOrder);
        return true;
    }

    @Override
    public Boolean addNewOrderProductsInOrder(Long orderId) {
        Order order = orderRepository.getById(orderId);
        Map<Integer, Product> orderProducts = order.getProducts();
        for (Map.Entry<Integer, Product> entry : orderProducts.entrySet()) {
            Integer amount = entry.getKey();
            Product product = entry.getValue();
            ProductInOrder newProductInOrder = new ProductInOrder();
            newProductInOrder.setProduct(product);
            newProductInOrder.setAmount(amount);
            productInOrderRepository.save(newProductInOrder);
        }
        return true;
    }

    @Override
    public Boolean cancelProductInOrder(Long productInOrderId) {
        ProductInOrder productInOrder = productInOrderRepository.getById(productInOrderId);
        productInOrder.setProductInOrderState(ProductInOrderState.CANCELED);
        productInOrderRepository.save(productInOrder);
        return true;
    }

    @Override
    public Boolean removeProductInOrder(ProductInOrder productInOrder) {
        productInOrderRepository.delete(productInOrder);
        return true;
    }

    @Override
    public Boolean editOrder(Map<Integer, Product> productsAndAmounts, Order order) {
        order.setProducts(productsAndAmounts);
        orderRepository.save(order);
        return true;
    }

    private Waiter userToWaiter(User user) {
        Waiter waiter = new Waiter();
        waiter.setRole(user.getRole());
        waiter.setName(user.getName());
        waiter.setLogin(user.getLogin());
        waiter.setPassword(user.getPassword());
        return waiter;
    }
}
