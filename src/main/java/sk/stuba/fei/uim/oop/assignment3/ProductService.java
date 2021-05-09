package sk.stuba.fei.uim.oop.assignment3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository){
        this.repository = repository;

        Product product1 = new Product();
        product1.setAmount(10);
        product1.setDescription("new");
        product1.setPrice(500);
        product1.setName("prvy");
        product1.setUnit("ones");

        this.repository.save(product1);


        Product product2 = new Product();
        product2.setAmount(10);
        product2.setDescription("new");
        product2.setPrice(500);
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
        return this.repository.getProductById(id);
    }
}
