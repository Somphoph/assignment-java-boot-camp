package bootcamp.java.assignment.product;

import bootcamp.java.assignment.product.exception.ProductNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/products")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getProducts(@RequestParam(required = false) String keyword) {
        List<Product> products = productService.findProducts(keyword);
        List<ProductResponse> productsResponse = new ArrayList<>();
        for (Product product : products) {
            productsResponse.add(new ProductResponse(product.getProductId(), product.getProductName(), product.getBrand(), product.getStar(), product.getPrice(), product.getPercDiscount(), product.getNormalPrice(), product.getDiscount(), product.getLastPromoDate(), ""));
        }
        return productsResponse;
    }

    @GetMapping("/{productId}")
    public ProductResponse getProduct(@PathVariable int productId) throws ProductNotFoundException {
        Product product = productService.findProductById(productId);
        return new ProductResponse(product.getProductId(), product.getProductName(), product.getBrand(), product.getStar(), product.getPrice(), product.getPercDiscount(), product.getNormalPrice(), product.getDiscount(), product.getLastPromoDate(), "");
    }
}
