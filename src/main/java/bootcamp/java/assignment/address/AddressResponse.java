package bootcamp.java.assignment.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class AddressResponse {
    private int addressId;
    private String address1;
    private String address2;
    private String district;
    private String postCode;
    @JsonProperty("default")
    private boolean isDefault;
}
