package bootcamp.java.assignment.cart;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class CartRepositoryTest {
    @Autowired
    private CartRepository cartRepository;

    @Test
    @DisplayName("ค้นหาข้อมูล Cart โดยใช้ UserId จะพบข้อมูล")
    void findByUserIdCase01() {
        Cart cart = new Cart();
        cart.setUserId(99);

        cartRepository.save(cart);
        Optional<Cart> cart1 = cartRepository.findByUserId(99);
        assertTrue(cart1.isPresent());
    }
}