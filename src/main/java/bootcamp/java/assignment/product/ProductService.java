package bootcamp.java.assignment.product;

import bootcamp.java.assignment.product.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    List<Product> findProducts(String keyword);

    Product findProductById(int productId) throws ProductNotFoundException;
}
