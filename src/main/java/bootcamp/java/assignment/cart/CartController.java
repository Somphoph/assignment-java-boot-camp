package bootcamp.java.assignment.cart;

import bootcamp.java.assignment.DefaultResponse;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users/{userId}/cart")
@RestController
public class CartController {
    private final CartService cartService;
    private final CartMapper cartMapper;

    public CartController(CartService cartService, CartMapper cartMapper) {
        this.cartService = cartService;
        this.cartMapper = cartMapper;
    }

    @PostMapping
    public DefaultResponse addToCart(@RequestBody AddToCartRequest addToCartRequest, @PathVariable int userId) {
        cartService.add(addToCartRequest.getProductId(), addToCartRequest.getAmount(), userId);
        return new DefaultResponse("success");
    }

    @GetMapping
    public CartResponse getCart(@PathVariable int userId) {
        Cart cart = cartService.findCartByUserId(userId);
        return cartMapper.cartToCartResponse(cart);
    }
}
