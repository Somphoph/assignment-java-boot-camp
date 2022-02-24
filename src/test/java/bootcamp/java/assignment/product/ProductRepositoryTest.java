package bootcamp.java.assignment.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("เมื่อใส่ Keyword ค้นหาด้วยคำที่มีคำนั้นอยู่ในชื่อของสินค้าต้องพบข้อมูล")
    void findByProductNameContainsIgnoreCase_with_keyword_found() {
        Product product = new Product(6, "Dell UltraSharp 43 4K USB-C Monitor U4320Q Monitor 4K UHD 3840 x2160 60Hz Warranty 3 Year 96% sRGB", "Dell", 32390.00, 32390.00, 0, null, 0, 5);
        productRepository.save(product);
        List<Product> result = productRepository.findByProductNameContainsIgnoreCase("4k");
        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("เมื่อใส่ Keyword ค้นหาด้วยคำที่ไม่มีคำนั้นอยู่ในชื่อของสินค้าต้องไม่พบข้อมูล")
    void findByProductNameContainsIgnoreCase_with_keyword_notfound() {
        Product product = new Product(6, "Dell UltraSharp 43 4K USB-C Monitor U4320Q Monitor 4K UHD 3840 x2160 60Hz Warranty 3 Year 96% sRGB", "Dell", 32390.00, 32390.00, 0, null, 0, 5);
        productRepository.save(product);
        List<Product> result = productRepository.findByProductNameContainsIgnoreCase("3k");
        assertTrue(result.isEmpty());
    }
}