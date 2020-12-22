package service.product;

import entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product save(Product product);

    Product findOne(Long id);
}
