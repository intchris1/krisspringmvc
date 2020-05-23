package com.example.springmvc.krisspringmvc.repository;

import com.example.springmvc.krisspringmvc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product, String> {
}
