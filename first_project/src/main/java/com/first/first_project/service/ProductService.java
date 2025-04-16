package com.first.first_project.service 
;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.first_project.Entity.ProductEntity;
import com.first.first_project.repo.ProductRepository;


@Service
public  class ProductService {
  @Autowired
  private ProductRepository productRepository;

  public List<ProductEntity> getAllProducts() {
    return productRepository.findAll();
  }

  public ProductEntity getProductById(long id) {
    return productRepository.findById(id).orElseThrow(() -> new RuntimeException("no product with this id"));
  }

  public ProductEntity createProduct(ProductEntity product) {
    return productRepository.save(product);
  }

  public ProductEntity updateProduct(
    Long id ,
    ProductEntity newProductInformation
  ) {
    ProductEntity oldProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("no product with this id"));

    if(newProductInformation.getTitle() != null) {
      oldProduct.setTitle(newProductInformation.getTitle());
    }
    if(newProductInformation.getDescription() != null) {
      oldProduct.setDescription(newProductInformation.getDescription());
    }

    if(newProductInformation.getPrice() != oldProduct.getPrice() && newProductInformation.getPrice() != null) {
      oldProduct.setPrice(newProductInformation.getPrice());
    }

    return productRepository.save(oldProduct);
  }

  public void deleteProduct(long id) {
    productRepository.deleteById(id);
  }
  

}