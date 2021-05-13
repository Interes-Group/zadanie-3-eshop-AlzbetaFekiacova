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
    public ResponseEntity<ShoppingCart> addCart(){
        return new ResponseEntity<>(this.service.create(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ShoppingCart getShoppingCartById(@PathVariable("id") Long id){
        return this.service.getShoppingCartById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteShoppingCartById(@PathVariable("id") Long id){
        this.service.deleteShoppingCartById(id);
    }

    @PostMapping("/{id}/add")
    public void addProduct(@PathVariable("id") Long id, @RequestBody CartItem item){
        this.service.addProduct(id, item);
    }

}
