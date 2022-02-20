package bootcamp.java.assignment.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @MockBean
    private ProductRepository productRepository;

    @Test
    @DisplayName("ค้นหาข้อมูล product ทั้งหมดแล้วจะได้ข้อมูลทั้งหมด")
    void getAllProduct() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "จอคอม AOC 24G2E 23.8\" IPS Gaming Monitor 144Hz", "AOC", 7990.0, 6990.0, 2, new Date(), 1000, 3));
        products.add(new Product(2, "จอคอม Dell Alienware 25 AW2521H 24.5\" IPS Gaming Monitor 360Hz", "Dell", 28990.0, 28990.0, 0, null, 0, 5));
        products.add(new Product(2, "LG Monitor Gaming 34\" รุ่น 34GL750-B IPS WFHD 144Hz", "LG", 17790, 16290, 8, null, 1500, 4));
        when(productRepository.findAll()).thenReturn(products);
        ResponseEntity<Product[]> resp = testRestTemplate.getForEntity("/products", Product[].class);
        assertTrue(resp.getStatusCode().is2xxSuccessful());
        assertEquals(3, Objects.requireNonNull(resp.getBody()).length);
    }
}