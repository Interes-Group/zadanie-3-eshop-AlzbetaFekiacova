package sk.stuba.fei.uim.oop.assignment3;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product create(ProductRequest request);
    Product getProductById(Long id) throws ProductNotFoundException;

}
