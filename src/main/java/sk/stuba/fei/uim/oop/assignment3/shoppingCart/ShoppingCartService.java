package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService implements IShoppingCartService{
    private ShoppingCartRepository repository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository repository){
        this.repository = repository;
    }

    @Override
    public ShoppingCart create() {
        ShoppingCart shoppingCart = new ShoppingCart();
        return this.repository.save(shoppingCart);

    }
}
