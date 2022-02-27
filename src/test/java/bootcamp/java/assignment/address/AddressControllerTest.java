package bootcamp.java.assignment.address;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @MockBean
    private AddressRepository addressRepository;

    @Test
    @DisplayName("ค้นหาข้อมูลที่อยู่โดยใช้ UserId ที่ได้บันทึกข้อมูลที่อยู่จะพบข้อมูล")
    void getAddressesCase01() {
        int userId = 99;
        Address address = new Address();
        address.setAddress1("999/333 หมู่บ้านเจริญจิต ซอยเจริญใจ");
        address.setAddress2("ถนนเจริญสุข แขวงรามอินทรา");
        address.setDistrict("เขตคันนายาว");
        address.setPostCode("10230");
        address.setDefault(true);
        address.setUserId(userId);
        List<Address> addressList = new ArrayList<>();
        addressList.add(address);

        when(addressRepository.findAddressByUserId(userId)).thenReturn(addressList);

        ResponseEntity<AddressResponse[]> resp = testRestTemplate.getForEntity("/api/users/" + userId + "/address", AddressResponse[].class);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals(1, Objects.requireNonNull(resp.getBody()).length);
    }
}