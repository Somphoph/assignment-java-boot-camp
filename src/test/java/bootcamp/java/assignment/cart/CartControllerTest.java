package bootcamp.java.assignment.cart;

import bootcamp.java.assignment.DefaultResponse;
import bootcamp.java.assignment.product.Product;
import bootcamp.java.assignment.product.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @MockBean
    private CartRepository cartRepository;
    @MockBean
    private ProductRepository productRepository;

    @Test
    @DisplayName("เมื่อเพิ่มสินค้าลงตะกร้าเมื่อในตะกร้าไม่มีสินค้าอยู่ ข้อมูลตะกร้าจะมีสินค้าเพิ่มเข้ามา 1 ชิ้น")
    void addToCartCase01() {
        int userId = 99;
        Product newProduct = new Product(3, "LG Monitor Gaming 34\" รุ่น 34GL750-B IPS WFHD 144Hz", "LG", 17790, 16290, 8, null, 1500, 4);
        when(productRepository.findById(3)).thenReturn(Optional.of(newProduct));

        Cart cart = new Cart(0, null, userId);
        when(cartRepository.save(cart)).thenReturn(cart);

        ResponseEntity<DefaultResponse> resp = testRestTemplate.postForEntity("/api/users/" + userId + "/cart", new AddToCartRequest(3, 1), DefaultResponse.class);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
    }

    @Test
    @DisplayName("เมื่อดึงข้อมูลตะกร้า จะได้ข้อมูลตะกร้าที่ไม่มีสินค้า")
    void getCartCase01() {
        int userId = 99;
        ResponseEntity<CartResponse> resp = testRestTemplate.getForEntity("/api/users/" + userId + "/cart", CartResponse.class);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertTrue(Objects.requireNonNull(resp.getBody()).getItems().isEmpty());
    }
}