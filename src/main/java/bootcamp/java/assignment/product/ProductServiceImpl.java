package bootcamp.java.assignment.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findProducts(String keyword) {
        if (keyword != null) {
            return productRepository.findByProductNameContainsIgnoreCase(keyword.toLowerCase());
        } else {
            return productRepository.findAll();
        }
    }
}
