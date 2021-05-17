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

    @Override
    public Product updateProduct(Long id, UpdateBody updateBody) {
        Optional<Product> optionalProduct = this.repository.findById(id);
        Product product = optionalProduct.orElseThrow(NotFoundException::new);
        if (updateBody.getName() != null) {
            product.setName(updateBody.getName());
        }
        if (updateBody.getDescription() != null) {
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
    public int getProductAmount(Long id) {
        Optional<Product> optionalProduct = this.repository.findById(id);
        Product product = optionalProduct.orElseThrow(NotFoundException::new);
        return product.getAmount();
    }

    @Override
    public int incrementAmount(Long id, AmountRequest amountRequest) {
        Optional<Product> optionalProduct = this.repository.findById(id);
        Product product = optionalProduct.orElseThrow(NotFoundException::new);
        product.setAmount(product.getAmount() + amountRequest.getAmount());
        this.repository.save(product);
        return product.getAmount();
    }
}
