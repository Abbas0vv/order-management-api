package com.example.studentmanagementapi.service;

import com.example.studentmanagementapi.dao.entity.CardEntity;
import com.example.studentmanagementapi.dao.entity.OrderEntity;
import com.example.studentmanagementapi.dao.entity.ProductEntity;
import com.example.studentmanagementapi.dao.repository.CardRepository;
import com.example.studentmanagementapi.dao.repository.OrderRepository;
import com.example.studentmanagementapi.dao.repository.ProductRepository;
import com.example.studentmanagementapi.dto.OrderRequestDto;
import com.example.studentmanagementapi.dto.OrderResponseDto;
import com.example.studentmanagementapi.exceptions.OrderNotFoundException;
import com.example.studentmanagementapi.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.studentmanagementapi.mapper.OrderMapper.mapEntityToOrderResponseDto;
import static com.example.studentmanagementapi.mapper.OrderMapper.mapToOrderEntity;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CardRepository cardRepository;


    public void addOrder(OrderRequestDto orderRequestDto) {

        var product = fetchProductIfExist(orderRequestDto.getProductId());
        checkProductAvailability(product, orderRequestDto.getProductCount());

        var card = fetchCardIfExist(orderRequestDto.getCardId());
        checkCardBalance(card, orderRequestDto.getAmount());

        product.setStock(product.getStock() - orderRequestDto.getProductCount());
        productRepository.save(product);

        card.setBalance(card.getBalance() - orderRequestDto.getAmount());
        cardRepository.save(card);

        orderRepository.save(mapToOrderEntity(orderRequestDto));
    }

    public OrderResponseDto getOrderById(Long orderId) {
        var order = fetchOrderIfExist(orderId);
        var card = fetchCardIfExist(order.getCardId());
        var product = fetchProductIfExist(order.getProductId());

        return mapEntityToOrderResponseDto(card, product, order);
    }

    private ProductEntity fetchProductIfExist(Long productId) {
        var product = productRepository.findById(productId);
        if (product.isEmpty())
            throw new ProductNotFoundException("Product not found");

        return product.get();
    }

    private void checkProductAvailability(ProductEntity product, Integer orderCount) {
        if (product.getStock() < orderCount)
            throw new RuntimeException("No available stock");
    }

    private CardEntity fetchCardIfExist(Long cardId) {
        var card = cardRepository.findById(cardId);
        if (card.isEmpty())
            throw new RuntimeException("Card not found");

        return card.get();
    }


    private void checkCardBalance(CardEntity cardEntity, double amount) {
        if (cardEntity.getBalance() < amount)
            throw new RuntimeException("Insufficient balance");
    }

    private OrderEntity fetchOrderIfExist(Long orderId) {
        var order = orderRepository.findById(orderId);
        if (order.isEmpty())
            throw new OrderNotFoundException("Order not found");

        return order.get();
    }

}
