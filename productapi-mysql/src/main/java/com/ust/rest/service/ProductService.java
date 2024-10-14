package com.ust.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.rest.model.Product;
import com.ust.rest.repository.Productrepository;

@Service
public class ProductService {
	@Autowired
	private Productrepository repo;
	
	public Product addProduct(Product product) {
		return repo.save(product);
	}
	public Optional<Product> getbyid(Long id) {
		return repo.findById(id);
	
		
	}
	public List<Product> getall(){
		return repo.findAll();
	}
	public List<Product> getprodbybrand(String brand){
		return repo.findByBrand(brand);
	}
	public Product updateproduct(Product pro)
	 {
		 if(repo.existsById(pro.getId()))
				 {
			 return repo.save(pro);
				 }
		 return null;
	 }
	public void delprod(Long id) {
		repo.deleteById(id);
	}

}
