package com.example.studentmanagementapi.mapper;

import com.example.studentmanagementapi.dao.entity.CardEntity;
import com.example.studentmanagementapi.dao.entity.OrderEntity;
import com.example.studentmanagementapi.dao.entity.ProductEntity;
import com.example.studentmanagementapi.dto.OrderRequestDto;
import com.example.studentmanagementapi.dto.OrderResponseDto;

import java.time.LocalDateTime;

public class OrderMapper {
    public static OrderEntity mapToOrderEntity(OrderRequestDto orderRequestDto){

        var entity = new OrderEntity();
        entity.setOrderDate(LocalDateTime.now());
        entity.setQuantity(orderRequestDto.getProductCount());
        entity.setCardId(orderRequestDto.getCardId());
        entity.setProductId(orderRequestDto.getProductId());
        entity.setTotalAmount(orderRequestDto.getAmount());

        return entity;
    }

    public static OrderResponseDto mapEntityToOrderResponseDto(CardEntity card, ProductEntity product, OrderEntity order){
        var response = new OrderResponseDto();
        response.setAmount(order.getTotalAmount());
        response.setCardBalance(card.getBalance());
        response.setCardNumber(card.getCardNumber());
        response.setProductName(product.getName());

        return response;
    }
}
