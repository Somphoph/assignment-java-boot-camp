package bootcamp.java.assignment.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddToCartRequest {
    private int productId;
    private int amount;
}
