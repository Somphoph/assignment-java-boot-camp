package bootcamp.java.assignment.address;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressMapper {
    public List<AddressResponse> addressesToAddressesResponse(List<Address> addrs) {
        List<AddressResponse> responses = new ArrayList<>();
        for (Address addr : addrs) {
            responses.add(addressToAddressResponse(addr));
        }
        return responses;
    }

    public AddressResponse addressToAddressResponse(Address addr) {
        AddressResponse addrResp = new AddressResponse();
        addrResp.setAddress1(addr.getAddress1());
        addrResp.setAddress2(addr.getAddress2());
        addrResp.setDistrict(addr.getDistrict());
        addrResp.setPostCode(addr.getPostCode());
        addrResp.setDefault(addr.isDefault());

        return addrResp;
    }
}
