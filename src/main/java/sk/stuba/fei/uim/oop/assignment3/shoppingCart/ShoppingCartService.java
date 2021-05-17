package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadQueryException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.IProductService;
import sk.stuba.fei.uim.oop.assignment3.product.Product;

import java.util.Optional;

@Service
public class ShoppingCartService implements IShoppingCartService {
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private IProductService productService;


    @Autowired
    public ShoppingCartService(ShoppingCartRepository repository) {
        this.shoppingCartRepository = repository;
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
    public ShoppingCart addProductToCart(Long id, CartItemRequest item) {
        Product product = this.productService.getProductById(item.getProductId());
        ShoppingCart cart = getShoppingCartById(id);
        if (cart.isPayed() || product.getAmount() - item.getAmount() < 0) {
            throw new BadQueryException();
        }
        boolean found = false;
        CartItem cartItem = new CartItem();
        for (CartItem ci : cart.getShoppingList()) {
            if (ci.getProductId().equals(item.getProductId())) {
                found = true;
                cartItem = ci;
                break;
            }
        }

        if (found) {
            cartItem.setAmount(cartItem.getAmount() + item.getAmount());


        } else {
            cartItem.setProductId(item.getProductId());
            cartItem.setAmount(item.getAmount());
            cart.getShoppingList().add(cartItem);
        }
        this.cartItemsRepository.save(cartItem);

        product.setAmount(product.getAmount() - item.getAmount());
        //this.shoppingCartRepository.save(cart);
        this.cartItemsRepository.save(cartItem);
        return cart;


    }

    @Override
    public String payForShopping(Long id) {
        ShoppingCart cart = this.getShoppingCartById(id);
        if (cart.isPayed()) {
            throw new BadQueryException();
        }
        Double price = 0D;
        for (CartItem item : cart.getShoppingList()) {
            Product product = this.productService.getProductById(item.getProductId());
            price += product.getPrice() * item.getAmount();
        }

        cart.setPayed(true);
        this.shoppingCartRepository.save(cart);
        return price.toString();
    }
}
