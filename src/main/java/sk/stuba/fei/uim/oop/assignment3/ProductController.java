package sk.stuba.fei.uim.oop.assignment3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping()
    public List<ProductResponse> getAllProducts() {
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public /*ProductResponse*/ ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request) {
        return new ResponseEntity<>(new ProductResponse(this.service.create(request)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return this.service.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable ("id") Long id, @RequestBody UpdateBody updateBody){
        Product p = this.service.getProductById(id);
        return this.service.updateProduct(id, updateBody);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        this.service.deleteProduct(id);
    }
    @GetMapping("/{id}/amount")
    public Amount getProductAmount(@PathVariable("id") Long id){
        return this.service.getProductAmount(id);
    }
}
