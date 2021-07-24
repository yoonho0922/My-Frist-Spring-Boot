package info.service;

import info.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryProductService {
    public List<Product> getProductList(){
        Product prod1 = new Product("gray", "t-shirt", 5000);
        Product prod2 = new Product("black", "coffe", 700);
        Product prod3 = new Product("blue", "jam", 1500);

        ArrayList<Product> products = new ArrayList();
        products.add(prod1);
        products.add(prod2);
        products.add(prod3);

        return products;
    }
}
