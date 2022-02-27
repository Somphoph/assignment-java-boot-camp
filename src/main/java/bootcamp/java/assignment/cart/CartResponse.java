package bootcamp.java.assignment.cart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Setter
@Getter
public class CartResponse {
    private List<CartItemResponse> items;

    private int totalAmount;
    private double totalPrice;
    private double totalNormalPrice;
    private double totalDiscount;
}