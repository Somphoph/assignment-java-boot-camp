package bootcamp.java.assignment.address;

import java.util.List;

public interface AddressMapper {
    List<AddressResponse> addressesToAddressesResponse(List<Address> addrs);

    AddressResponse addressToAddressResponse(Address addr);
}
