package service.product;

import entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import repository.ProductRepository;

import java.util.List;

public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findOne(Long id) {
        return productRepository.findOne(id);
    }
}
