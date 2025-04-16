package com.first.first_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first.first_project.Entity.ProductEntity;
import com.first.first_project.service.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public List<ProductEntity> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/{id}")
  public ProductEntity getProductById(@PathVariable("id") long id) {
    return productService.getProductById(id);
  }

  @PostMapping
  public ProductEntity createProduct(@RequestBody ProductEntity product) {
    return productService.createProduct(product);
  }
  @PutMapping("/{id}")
  public ProductEntity updateProduct(@PathVariable("id") long id, @RequestBody ProductEntity newProduct) {

    return productService.updateProduct(id,newProduct) ;  
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable("id") long id) {
    productService.deleteProduct(id);
  }

}
