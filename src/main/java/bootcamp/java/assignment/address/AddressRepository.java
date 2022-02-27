package bootcamp.java.assignment.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAddressByUserId(Integer userId);
}
