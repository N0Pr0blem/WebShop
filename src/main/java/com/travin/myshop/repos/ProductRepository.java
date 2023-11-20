package com.travin.myshop.repos;

import com.travin.myshop.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Integer> {
    Product findByName(String name);
}
