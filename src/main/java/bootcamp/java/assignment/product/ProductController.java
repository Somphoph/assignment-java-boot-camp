package bootcamp.java.assignment.product;

import bootcamp.java.assignment.product.exception.ProductNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String keyword) {
        return productService.findProducts(keyword);
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable int productId) throws ProductNotFoundException {
        return productService.findProductById(productId);
    }
}
