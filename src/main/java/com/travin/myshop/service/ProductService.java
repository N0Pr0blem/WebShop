package com.travin.myshop.service;

import com.travin.myshop.domain.Product;
import com.travin.myshop.exception.InputDataException;
import com.travin.myshop.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static org.apache.commons.lang3.StringUtils.isNumeric;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ArrayList<Product> getAllProducts() {
        return (ArrayList<Product>) productRepository.findAll();
    }

    public void addNewProduct(String name, String price, String company, String description, String count, String image) throws InputDataException {
        if (name.isEmpty() || price.isEmpty() || company.isEmpty() || count.isEmpty() || image.isEmpty()) {
            throw new InputDataException("Wrong product data");
        }
        if (!isNumeric(price) || isNumeric(count)) {
            throw new InputDataException("Wrong price or count");
        } else {
            Product product = new Product(name, Double.parseDouble(price), company, description, Integer.parseInt(count), image);
            productRepository.save(product);
        }
    }

    public void updateProduct(Product product, String name, String price, String company, String description, String count, String image) throws InputDataException {
        if (name.isEmpty() || price.isEmpty() || company.isEmpty() || count.isEmpty() || image.isEmpty()) {
            throw new InputDataException("Wrong product data");
        }
        if (!isNumeric(price) || isNumeric(count)) {
            throw new InputDataException("Wrong price or count");
        } else {
            product.setName(name);
            product.setPrice(Double.parseDouble(price));
            product.setCompany(company);
            product.setDescription(description);
            product.setCount(Integer.parseInt(count));
            product.setImage(image);
            productRepository.save(product);
        }
    }
    public void buyProduct(Product product, String str_count) throws InputDataException {
        int count;
        if(isNumeric(str_count)){
            count = Integer.parseInt(str_count);
            if ( count > 0 && count <= product.getCount()) {
                product.setCount(product.getCount() - count);
                productRepository.save(product);
            }
            else{
                throw new InputDataException("Your count much more than product count or low than zero");
            }
        }
        else{
            throw new InputDataException("Wrong count");
        }

    }
}
