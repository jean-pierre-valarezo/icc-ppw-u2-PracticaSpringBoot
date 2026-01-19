package ec.edu.ups.icc.fundamentos01.products.mappers;

import ec.edu.ups.icc.fundamentos01.products.dtos.ProductResponseDto;
import ec.edu.ups.icc.fundamentos01.products.entities.ProductEntity;

public class ProductMapper {

    public static ProductResponseDto toDto(ProductEntity entity) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        return dto;
    }
}
