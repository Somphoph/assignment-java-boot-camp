package bootcamp.java.assignment.cart;

import bootcamp.java.assignment.DefaultResponse;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users/{userId}/cart")
@RestController
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public DefaultResponse addToCart(@RequestBody AddToCartRequest addToCartRequest, @PathVariable int userId) {
        cartService.add(addToCartRequest.getProductId(), addToCartRequest.getAmount(), userId);
        return new DefaultResponse("success");
    }
}
