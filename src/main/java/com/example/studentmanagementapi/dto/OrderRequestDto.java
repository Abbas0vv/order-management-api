package com.example.studentmanagementapi.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequestDto {
    private Long productId;
    private Long cardId;
    private Integer productCount;
    private Double amount;
}
