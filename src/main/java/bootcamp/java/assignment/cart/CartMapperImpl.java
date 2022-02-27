package bootcamp.java.assignment.cart;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CartMapperImpl implements CartMapper {
    @Override
    public CartResponse cartToCartResponse(Cart cart) {
        CartResponse response = new CartResponse();
        response.setItems(new ArrayList<>());
        for (CartItem item : cart.getItems()) {
            CartItemResponse itemResp = new CartItemResponse();
            itemResp.setAmount(item.getAmount());
            itemResp.setPrice(item.getProduct().getPrice());
            itemResp.setTotal(item.getProduct().getPrice() * item.getAmount());
            response.getItems().add(itemResp);
            response.setTotalAmount(response.getTotalAmount() + itemResp.getAmount());
            response.setTotalPrice(response.getTotalPrice() + itemResp.getPrice());
            response.setTotalDiscount(response.getTotalDiscount() + itemResp.getDiscount());
            response.setTotalNormalPrice(response.getTotalNormalPrice() + itemResp.getNormalPrice());
        }
        return response;
    }
}
