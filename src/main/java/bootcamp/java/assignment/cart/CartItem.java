package bootcamp.java.assignment.cart;

import bootcamp.java.assignment.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Product product;
    private int amount;
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;
}
