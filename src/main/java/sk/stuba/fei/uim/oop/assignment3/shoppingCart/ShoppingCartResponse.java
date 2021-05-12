package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

import lombok.Getter;

import java.util.List;

@Getter
public class ShoppingCartResponse {
    private Long id;
    private boolean payed;
    private List<CartItem> items;

    public ShoppingCartResponse(ShoppingCart shoppingCart) {
        this.id = shoppingCart.getId();
        this.payed = shoppingCart.isPayed();
        this.items = shoppingCart.getShoppingList();

    }
}
