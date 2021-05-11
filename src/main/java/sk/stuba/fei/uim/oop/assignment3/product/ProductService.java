package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;


        Product product1 = new Product();
        product1.setAmount(10);
        product1.setDescription("new");
        product1.setPrice(500.0);
        product1.setName("prvy");
        product1.setUnit("ones");

        this.repository.save(product1);


        Product product2 = new Product();
        product2.setAmount(10);
        product2.setDescription("new");
        product2.setPrice(500.0);
        product2.setName("druhy");
        product2.setUnit("ones");

        this.repository.save(product2);

    }

    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product create(ProductRequest request) {
        Product product = new Product();
        product.setId(request.getId());
        product.setUnit(request.getUnit());
        product.setId(request.getId());
        product.setPrice(request.getPrice());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setAmount(request.getAmount());
        return this.repository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = this.repository.findById(id);
        return optionalProduct.orElseThrow(NotFoundException::new);
    }

//    private String name;
//    private String description;
//    private int amount;
//    private String unit;
//    private int price;
//}

    @Override
    public Product updateProduct(Long id, UpdateBody updateBody) {
        Optional<Product> optionalProduct = this.repository.findById(id);
        Product product = optionalProduct.orElseThrow(NotFoundException::new);
        if (updateBody.getName() != null) {
            product.setName(updateBody.getName());
        }
        if(updateBody.getDescription() != null){
            product.setDescription(updateBody.getDescription());
        }

        return this.repository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> optionalProduct = this.repository.findById(id);
        Product product = optionalProduct.orElseThrow(NotFoundException::new);
        this.repository.delete(product);
    }

    @Override
    public Amount getProductAmount(Long id) {
        Optional<Product> optionalProduct = this.repository.findById(id);
        Product product = optionalProduct.orElseThrow(NotFoundException::new);
        Amount a = new Amount();
        a.setAmount(product.getAmount());
        return a;
    }

    @Override
    public Amount incrementAmount(Long id, Amount amount) {
        Optional<Product> optionalProduct = this.repository.findById(id);
        Product product = optionalProduct.orElseThrow(NotFoundException::new);
        product.setAmount(product.getAmount() + amount.getAmount());
        return new Amount(product.getAmount());
    }
}