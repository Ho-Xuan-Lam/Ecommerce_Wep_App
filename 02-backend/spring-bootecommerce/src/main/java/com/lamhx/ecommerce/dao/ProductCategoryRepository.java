package com.lamhx.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.lamhx.ecommerce.entity.ProductCategory;

@CrossOrigin("http://localhost:4200") // Accept call from web browser scripts for this origin
@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
// collectionResourceRel = "productCategory": Name of JSON entry
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>{

}
