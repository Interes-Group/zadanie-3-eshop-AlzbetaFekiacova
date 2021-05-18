package sk.stuba.fei.uim.oop.assignment3.cartItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CartItemRequest {
    private Long productId;
    private int amount;
}
