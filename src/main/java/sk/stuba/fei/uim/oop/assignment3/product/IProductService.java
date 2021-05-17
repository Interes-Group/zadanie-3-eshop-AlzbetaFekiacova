package sk.stuba.fei.uim.oop.assignment3.product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product create(ProductRequest request);
    Product getProductById(Long id);
    Product updateProduct(Long id, UpdateBody updateBody);
    void deleteProduct(Long id);

    int getProductAmount(Long id);

    int incrementAmount(Long id, AmountRequest amountRequest);
}
