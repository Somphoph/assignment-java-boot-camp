package bootcamp.java.assignment.cart;

import bootcamp.java.assignment.product.Product;
import bootcamp.java.assignment.product.ProductRepository;
import bootcamp.java.assignment.product.exception.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("เมื่อเพิ่มสินค้าลงตะกร้าโดยในตะกร้ามีสินค้าอยู่แล้ว ตะกร้าจะมีสินค้าเพิ่มขึ้นจาก 1 เป็น 2 ชิ้น")
    void addCase01() {
        int userId = 99;
        Cart cart = new Cart(0, new HashSet<>(), userId);
        Product product = new Product(1, "จอคอม AOC 24G2E 23.8\" IPS Gaming Monitor 144Hz", "AOC", 7990.0, 6990.0, 2, new Date(), 1000, 3);
        cart.getItems().add(new CartItem(0, product, 1, 7990, 6990, 1000, 6990, cart));
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
        when(cartRepository.save(cart)).thenReturn(cart);
        Product newProduct = new Product(3, "LG Monitor Gaming 34\" รุ่น 34GL750-B IPS WFHD 144Hz", "LG", 17790, 16290, 8, null, 1500, 4);
        when(productRepository.findById(3)).thenReturn(Optional.of(newProduct));
        CartService cartService = new CartServiceImpl(cartRepository, productRepository);
        Cart result = cartService.add(3, 1, userId);

        Assertions.assertEquals(2, result.getItems().size());
    }

    @Test
    @DisplayName("เมื่อเพิ่มสินค้าลงตะกร้าเมื่อในตะกร้าไม่มีสินค้าอยู่ ข้อมูลตะกร้าจะมีสินค้าเพิ่มเข้ามา 1 ชิ้น")
    void addCase02() {
        int userId = 99;
        Product newProduct = new Product(3, "LG Monitor Gaming 34\" รุ่น 34GL750-B IPS WFHD 144Hz", "LG", 17790, 16290, 8, null, 1500, 4);
        when(productRepository.findById(3)).thenReturn(Optional.of(newProduct));

        Cart cart = new Cart(0, null, userId);
        when(cartRepository.save(cart)).thenReturn(cart);

        CartService cartService = new CartServiceImpl(cartRepository, productRepository);
        Cart result = cartService.add(3, 1, userId);
        Assertions.assertEquals(1, result.getItems().size());
    }

    @Test
    @DisplayName("เมื่อเพิ่มสินค้าที่ไม่มีในระบบจะได้ ProductNotFoundException")
    void addCase03() {
        int userId = 99;
        Cart cart = new Cart(0, null, userId);
        when(cartRepository.save(cart)).thenReturn(cart);

        CartService cartService = new CartServiceImpl(cartRepository, productRepository);
        Assertions.assertThrows(ProductNotFoundException.class, () -> cartService.add(555, 1, userId));
    }
}