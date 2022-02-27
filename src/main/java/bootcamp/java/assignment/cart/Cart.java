package bootcamp.java.assignment.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    @OneToMany(mappedBy = "cart")
    private Set<CartItem> items;
    private int userId;
}
