package com.codegym.thithuchanh.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String nameProduct;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "productTypeId", nullable = false)
    private ProductType productType;

    @OneToMany(mappedBy = "product")
    private List<Order> orders;
}
