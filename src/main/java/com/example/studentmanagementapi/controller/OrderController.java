package com.example.studentmanagementapi.controller;

import com.example.studentmanagementapi.dto.OrderRequestDto;
import com.example.studentmanagementapi.dto.OrderResponseDto;
import com.example.studentmanagementapi.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrder(@RequestBody @Valid OrderRequestDto orderRequestDto){
        orderService.addOrder(orderRequestDto);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDto getOrderById(@PathVariable Long orderId){
        return orderService.getOrderById(orderId);
    }
}
