package sk.stuba.fei.uim.oop.assignment3.cartItem;


import lombok.Getter;

@Getter
public class CartItemResponse {
    private Long productId;
    private int amount;

    public CartItemResponse(CartItem cartItem){
        this.productId = cartItem.getProductId();
        this.amount = cartItem.getAmount();
    }
}
