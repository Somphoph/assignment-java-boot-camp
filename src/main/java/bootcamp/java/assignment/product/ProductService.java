package bootcamp.java.assignment.product;

import bootcamp.java.assignment.product.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findProducts(String keyword) {
        if (keyword != null) {
            return productRepository.findByProductNameContainsIgnoreCase(keyword.toLowerCase());
        } else {
            return productRepository.findAll();
        }
    }

    public Product findProductById(int productId) throws ProductNotFoundException {
        Optional<Product> result = productRepository.findById(productId);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ProductNotFoundException();
        }
    }
}
