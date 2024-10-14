package com.ust.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.rest.model.Product;

@Repository
public interface Productrepository extends JpaRepository<Product,Long> {
	public List<Product> findByBrand(String brand);

}