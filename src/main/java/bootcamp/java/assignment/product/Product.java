package bootcamp.java.assignment.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Product {
    @Id
    private int productId;
    private String productName;
    private String brand;
    private double normalPrice;
    private double price;
    private int percDiscount;
    private Date lastPromoDate;
    private double discount;
    private int star;
}
