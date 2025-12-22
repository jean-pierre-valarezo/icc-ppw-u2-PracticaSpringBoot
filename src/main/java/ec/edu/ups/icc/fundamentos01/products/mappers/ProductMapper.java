package ec.edu.ups.icc.fundamentos01.products.mappers;

import ec.edu.ups.icc.fundamentos01.products.dtos.ProductResponseDto;
import ec.edu.ups.icc.fundamentos01.products.entities.Product;

public class ProductMapper {
    public static Product toEntity(int id, String name, double price) {
        return new Product(id, name, price);
    }

    public static ProductResponseDto toResponse(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.id = product.getId();
        dto.name = product.getName();
        dto.price = product.getPrice();
        return dto;
    }
}
