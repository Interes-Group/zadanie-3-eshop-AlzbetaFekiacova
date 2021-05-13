package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

public interface IShoppingCartService {
    ShoppingCart create();

    ShoppingCart getShoppingCartById(Long id);

    void deleteShoppingCartById(Long id);

    void addProduct(Long id, CartItem item);
}
