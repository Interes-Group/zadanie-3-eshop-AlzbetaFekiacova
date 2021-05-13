package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

import lombok.Getter;

import java.util.List;

@Getter
public class ShoppingCartResponse {
    private Long id;
    private List<CartItem> shoppingList;
    private boolean payed;

    public ShoppingCartResponse(ShoppingCart shoppingCart) {
        this.id = shoppingCart.getId();
        this.shoppingList = shoppingCart.getShoppingList();
        this.payed = shoppingCart.isPayed();
    }
}
