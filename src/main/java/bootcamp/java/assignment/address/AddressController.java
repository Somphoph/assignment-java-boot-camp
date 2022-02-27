package bootcamp.java.assignment.address;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/users/{userId}/address")
@RestController
public class AddressController {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressController(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @GetMapping
    public List<AddressResponse> getAddresses(@PathVariable int userId) {
        List<Address> addrs = addressRepository.findAddressByUserId(userId);
        return addressMapper.addressesToAddressesResponse(addrs);
    }
}
