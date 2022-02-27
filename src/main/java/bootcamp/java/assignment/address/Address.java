package bootcamp.java.assignment.address;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Integer addressId;

    private String address1;
    private String address2;
    private String district;
    private String postCode;
    private boolean isDefault;
    private int userId;
}
