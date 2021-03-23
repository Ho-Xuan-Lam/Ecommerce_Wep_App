package com.lamhx.ecommerce.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.lamhx.ecommerce.entity.Product;

@CrossOrigin("http://localhost:4200") // Accept call from web browser scripts for this origin
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
	
	//seclect * from product p where p.name like contact('%', :name, '%')
	Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
	
}
