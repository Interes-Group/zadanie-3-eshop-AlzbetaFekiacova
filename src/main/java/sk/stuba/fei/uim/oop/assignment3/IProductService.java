package sk.stuba.fei.uim.oop.assignment3;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product create(ProductRequest request);
    Product getProductById(Long id);
    Product updateProduct(Long id, UpdateBody updateBody);
    void deleteProduct(Long id);

    Amount getProductAmount(Long id);
}
