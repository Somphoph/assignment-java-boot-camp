package bootcamp.java.assignment.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductResponse {
    private int productId;
    private String productName;
    private String brand;
    private int star;
    private double price;
    private int percDiscount;
    private double normalPrice;
    private double discount;
    private Date lastPromoDate;
    private String thumbnail;
}
