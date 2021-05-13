package sk.stuba.fei.uim.oop.assignment3.shoppingCart;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    IShoppingCartService service;

    @PostMapping
    public ResponseEntity<ShoppingCartResponse> addCart(){
        return new ResponseEntity<>(new ShoppingCartResponse(this.service.create()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ShoppingCartResponse getShoppingCartById(@PathVariable("id") Long id){
        return new ShoppingCartResponse(this.service.getShoppingCartById(id));
    }
    @DeleteMapping("/{id}")
    public void deleteShoppingCartById(@PathVariable("id") Long id){
        this.service.deleteShoppingCartById(id);
    }

    @PostMapping("/{id}/add")
    public ShoppingCartResponse addProductToCart(@PathVariable("id") Long id, @RequestBody CartItemRequest request) {
        return new ShoppingCartResponse(this.service.addProductToCart(id, request));
    }
    @GetMapping("/{id}/pay")
    public String payForShopping(@PathVariable("id") Long id){
        return this.service.payForShopping(id);
    }

}
