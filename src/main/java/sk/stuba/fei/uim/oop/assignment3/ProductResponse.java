package sk.stuba.fei.uim.oop.assignment3;

import lombok.Getter;

@Getter
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private int amount;
    private String unit;
    private Double price;

    public ProductResponse(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();

        this.amount = product.getAmount();
        this.unit = product.getUnit();
        this.price = product.getPrice();

    }
}
