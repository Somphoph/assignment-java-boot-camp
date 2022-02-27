package bootcamp.java.assignment.cart;

import bootcamp.java.assignment.product.Product;
import bootcamp.java.assignment.product.ProductRepository;
import bootcamp.java.assignment.product.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Cart add(int productId, int amount, int userId) {
        Optional<Cart> optCart = cartRepository.findByUserId(userId);
        Cart cart;
        if (optCart.isEmpty()) {
            cart = createNewCart(userId);
        } else {
            cart = optCart.get();
        }
        CartItem item = new CartItem();
        Optional<Product> optProduct = productRepository.findById(productId);
        if (optProduct.isEmpty()) {
            throw new ProductNotFoundException();
        }
        Product product = optProduct.get();
        item.setProduct(product);
        item.setAmount(amount);
        cart.getItems().add(item);
        return cartRepository.save(cart);
    }

    @Override
    public Cart findCartByUserId(int userId) {
        Optional<Cart> optCart = cartRepository.findByUserId(userId);
        if (optCart.isEmpty()) {
            return createNewCart(userId);
        } else {
            return optCart.get();
        }
    }

    private Cart createNewCart(int userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setItems(new HashSet<>());
        return cart;
    }
}
