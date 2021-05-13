package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadQueryException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.IProductService;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRepository;
import sk.stuba.fei.uim.oop.assignment3.product.ProductResponse;

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
//        Product product = new Product();
//        ProductResponse productResponse= this.productService.getProductById(item.getProductId());
//        product.setId(productResponse.getId());
//        product.setDescription(productResponse.getDescription());
//        product.setPrice(productResponse.getPrice());
//        product.setName(productResponse.getName());
//        product.setUnit(productResponse.getUnit());
//        product.setAmount(product.getAmount());
        Product product = this.productService.getProductById(item.getProductId());
        ShoppingCart cart = getShoppingCartById(id);
        if (cart.isPayed()) {
            throw new BadQueryException();
        }
        if (product.getAmount() - item.getAmount() < 0) {
            throw new BadQueryException();
        }
        boolean found = false;
        CartItem cartItem = new CartItem();
        for (CartItem ci : cart.getShoppingList()) {
            if (ci.getProductId() == item.getProductId()) {
                found = true;
                cartItem = ci;
            }
        }

        if (found) {
            cartItem.setAmount(cartItem.getAmount() + item.getAmount());
            this.cartItemsRepository.save(cartItem);



        } else {
            cartItem.setProductId(item.getProductId());
            cartItem.setAmount(item.getAmount());
            cart.getShoppingList().add(cartItem);
            this.cartItemsRepository.save(cartItem);
        }

        product.setAmount(product.getAmount() - item.getAmount());
        this.shoppingCartRepository.save(cart);
        return cart;


    }

    @Override
    public String payForShopping(Long id) {
        ShoppingCart cart = this.getShoppingCartById(id);
        if(cart.isPayed()){
            throw new BadQueryException();
        }
        int price = 0;
        for(CartItem item : cart.getShoppingList()){
            Product product = this.productService.getProductById(item.getProductId());
            //ProductResponse productResponse = this.productService.getProductById(item.getProductId());
            price += product.getPrice()*item.getAmount();
        }

        cart.setPayed(true);
        this.shoppingCartRepository.save(cart);
        return ""+price;
    }
}
