package com.codegym.thithuchanh.Dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class OrderDto {

    private Long orderId;

    @NotNull(message = "Ngày mua không được để trống")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Future(message = "Ngày mua phải lớn hơn ngày hiện tại")
    private LocalDate purchaseDate;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 1, message = "Số lượng phải là số nguyên dương")
    private Integer quantity;

    private Long productId;

    private String productType;

    private String productName;
}
