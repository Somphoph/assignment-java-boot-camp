package bootcamp.java.assignment.address;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class AddressRepositoryTest {
    @Autowired
    private AddressRepository addressRepository;

    @Test
    @DisplayName("ค้นหาข้อมูลที่อยู่โดยใช้ UserId ที่ได้บันทึกข้อมูลที่อยู่จะพบข้อมูล")
    void findAddressByUserIdCase01() {
        int userId = 99;
        Address address = new Address();
        address.setAddress1("999/333 หมู่บ้านเจริญจิต ซอยเจริญใจ");
        address.setAddress2("ถนนเจริญสุข แขวงรามอินทรา");
        address.setDistrict("เขตคันนายาว");
        address.setPostCode("10230");
        address.setDefault(true);
        address.setUserId(userId);

        addressRepository.save(address);

        List<Address> addrs = addressRepository.findAddressByUserId(userId);
        Assertions.assertEquals(1, addrs.size());
    }
}