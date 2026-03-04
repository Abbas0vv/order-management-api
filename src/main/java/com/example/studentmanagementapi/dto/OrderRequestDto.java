package com.example.studentmanagementapi.dto;


import jakarta.validation.constraints.Max;
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
    @Max(50)
    private Integer productCount;
    private Double amount;
}
