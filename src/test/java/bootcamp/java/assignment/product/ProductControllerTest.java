package bootcamp.java.assignment.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @MockBean
    private ProductRepository productRepository;

    List<Product> products;

    @BeforeEach
    void beforeTest() {
        products = new ArrayList<>();
        products.add(new Product(1, "จอคอม AOC 24G2E 23.8\" IPS Gaming Monitor 144Hz", "AOC", 7990.0, 6990.0, 2, new Date(), 1000, 3));
        products.add(new Product(2, "จอคอม Dell Alienware 25 AW2521H 24.5\" IPS Gaming Monitor 360Hz", "Dell", 28990.0, 28990.0, 0, null, 0, 5));
        products.add(new Product(3, "LG Monitor Gaming 34\" รุ่น 34GL750-B IPS WFHD 144Hz", "LG", 17790, 16290, 8, null, 1500, 4));
        products.add(new Product(4, "BenQ PD2725U 27นิ้ว 4K 100% sRGB Thunderbolt 3 Graphic Design Monitor (จอคอมงานกราฟฟิค, จอมอนิเตอร์ 4k 27นิ้ว)", "BenQ", 31900, 31900, 0, null, 0, 0));
        products.add(new Product(5, "SAMSUNG MONITOR LS32AM700UEXXT (4K Smart Monitor)", "Samsung", 9700, 9700, 0, null, 0, 0));
        products.add(new Product(6, "Dell UltraSharp 43 4K USB-C Monitor U4320Q Monitor 4K UHD 3840 x2160 60Hz Warranty 3 Year 96% sRGB", "Dell", 32390.00, 32390.00, 0, null, 0, 5));
    }

    @Test
    @DisplayName("ค้นหาข้อมูล product ทั้งหมดแล้วจะได้ข้อมูลทั้งหมด")
    void getAllProduct() {
        when(productRepository.findAll()).thenReturn(products);
        ResponseEntity<Product[]> resp = testRestTemplate.getForEntity("/products", Product[].class);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals(6, Objects.requireNonNull(resp.getBody()).length);
    }

    @Test
    @DisplayName("ค้นหาข้อมูล product ด้วยคำว่า 4k จะได้ข้อมูล 3 ชิ้น")
    void getProductsByKeyword() {
        String keyword = "4k";
        List<Product> filtered = products.stream().filter(product -> product.getProductName().toLowerCase().contains(keyword)).collect(Collectors.toList());
        when(productRepository.findByProductNameContainsIgnoreCase(keyword)).thenReturn(filtered);
        ResponseEntity<Product[]> resp = testRestTemplate.getForEntity("/products?keyword=" + keyword, Product[].class);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals(3, Objects.requireNonNull(resp.getBody()).length);
    }

    @Test
    @DisplayName("ค้นหาข้อมูล product ด้วย Id จะได้ข้อมูลสินค้า 1 ชิ้น")
    void getProductByProductId() {
        int id = 1;
        List<Product> filtered = products.stream().filter(product -> product.getProductId() == id).collect(Collectors.toList());
        when(productRepository.findById(id)).thenReturn(Optional.of(filtered.get(0)));
        ResponseEntity<Product> resp = testRestTemplate.getForEntity("/products/" + id, Product.class);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals("จอคอม AOC 24G2E 23.8\" IPS Gaming Monitor 144Hz", Objects.requireNonNull(resp.getBody()).getProductName());
    }

    @Test
    @DisplayName("ค้นหาข้อมูลด้วย Id ที่ไม่มีจะได้ HTTP Code 404")
    void getProductByProductId_whenNotfound_thenError404() {
        int id = 99;
        ResponseEntity<Product> resp = testRestTemplate.getForEntity("/products/" + id, Product.class);
        assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
    }
}