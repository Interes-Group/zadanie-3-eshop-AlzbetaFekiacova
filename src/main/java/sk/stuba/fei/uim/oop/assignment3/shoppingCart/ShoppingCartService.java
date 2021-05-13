package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRepository;

import java.util.Optional;

@Service
public class ShoppingCartService implements IShoppingCartService{
    private ShoppingCartRepository shoppingCartRepository;
    private ProductRepository productRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository repository, ProductRepository productRepository){
        this.shoppingCartRepository = repository;
        this.productRepository = productRepository;
    }

    @Override
    public ShoppingCart create() {
        ShoppingCart shoppingCart = new ShoppingCart();
        return this.shoppingCartRepository.save(shoppingCart);

    }

    @Override
    public ShoppingCart getShoppingCartById(Long id) {
        Optional<ShoppingCart> optionalShoppingCart = this.shoppingCartRepository.findById(id);
        return optionalShoppingCart.orElseThrow(NotFoundException::new);
    }

    @Override
    public void deleteShoppingCartById(Long id) {
        Optional<ShoppingCart> optionalShoppingCart = this.shoppingCartRepository.findById(id);
        ShoppingCart shoppingCart = optionalShoppingCart.orElseThrow(NotFoundException::new);
        this.shoppingCartRepository.delete(shoppingCart);
    }

    @Override
    public void addProduct(Long id, CartItem item) {

    }
}
