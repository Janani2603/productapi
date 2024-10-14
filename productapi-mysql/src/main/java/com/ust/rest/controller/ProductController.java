package com.ust.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.rest.model.Product;
import com.ust.rest.service.ProductService;

@RestController
@RequestMapping("/product/api1.0")//root of resource/controller
public class ProductController {
	@Autowired
	private ProductService service;
	
	Logger logger=Logger.getLogger("ProductController.class");
	ArrayList<Product>prodlist=new ArrayList<>();
	{
	prodlist.add(new Product(1,"bata","firm",20,20000));
	}
	@GetMapping(value="/product/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<Product>> getbyId(@PathVariable long id){
		Optional<Product> prod=service.getbyid(id);
	    return ResponseEntity.ok(prod);
	}
	@GetMapping(value="/productsinfo",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> displayprods(){
		return ResponseEntity.ok(service.getall());
	}

	@GetMapping(value="/prod/{brand}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getbybrand(@PathVariable String brand){
		
		return ResponseEntity.ok(service.getprodbybrand(brand));
	}
	@PostMapping(value="/addprod",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> addprod(@RequestBody Product product){
		logger.info("entered post method"+product);
		Product prod=service.addProduct(product);
		logger.info("persisted product"+prod);
		return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(prod);
		
	}
	@PutMapping(value="/modify")
	public  ResponseEntity<Product> updateProduct(@RequestBody Product prod)
	{
	 Product result = service.updateproduct(prod);
	 if(result!=null)
	 {
		 return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(prod);
	 }
		 
		
		         return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
	}
	/*@PutMapping(value="/modify",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> updatreprod(@RequestBody Product product){
		
		return null;
	}*/
	@DeleteMapping(value="/delete/{id}")
	public void removeprod(@PathVariable long id) {
		service.delprod(id);
		}
 
}
