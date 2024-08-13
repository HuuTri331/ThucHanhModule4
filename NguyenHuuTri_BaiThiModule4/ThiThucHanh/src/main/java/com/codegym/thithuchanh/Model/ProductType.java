package com.codegym.thithuchanh.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "product_type")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productTypeId;

    @Column(nullable = false)
    private String nameType;

    @OneToMany(mappedBy = "productType") // Updated to match the field name in the Product entity
    private List<Product> products;
}
