package bootcamp.java.assignment.cart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class CartItemResponse {
	private int productId;
	private String productName;
	private int amount;
	private double price;
	private double total;
	private double discount;
	private double normalPrice;
}
