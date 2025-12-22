package ec.edu.ups.icc.fundamentos01.products.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ec.edu.ups.icc.fundamentos01.products.dtos.*;
import ec.edu.ups.icc.fundamentos01.products.entities.Product;
import ec.edu.ups.icc.fundamentos01.products.mappers.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

    private List<Product> products = new ArrayList<>();
    private int currentId = 1;

    @Override
    public List<ProductResponseDto> findAll() {
        return products.stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

@Override
public Object findOne(int id) {
    for (Product product : products) {
        if (product.getId() == id) {
            return ProductMapper.toResponse(product);
        }
    }
    return new Object() {
        public String error = "Product not found";
    };
}

    @Override
    public ProductResponseDto create(CreateProductDto dto) {
        Product product = ProductMapper.toEntity(currentId++, dto.name, dto.price);
        products.add(product);
        return ProductMapper.toResponse(product);
    }

    @Override
    public Object update(int id, UpdateProductDto dto) {
        Product product = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (product == null)
            return new Object() { public String error = "Product not found"; };

        product.setName(dto.name);
        product.setPrice(dto.price);

        return ProductMapper.toResponse(product);
    }

    @Override
    public Object partialUpdate(int id, PartialUpdateProductDto dto) {
        Product product = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (product == null)
            return new Object() { public String error = "Product not found"; };

        if (dto.name != null) product.setName(dto.name);
        if (dto.price != null) product.setPrice(dto.price);

        return ProductMapper.toResponse(product);
    }

    @Override
    public Object delete(int id) {
        boolean removed = products.removeIf(p -> p.getId() == id);
        if (!removed)
            return new Object() { public String error = "Product not found"; };

        return new Object() { public String message = "Deleted successfully"; };
    }
}
