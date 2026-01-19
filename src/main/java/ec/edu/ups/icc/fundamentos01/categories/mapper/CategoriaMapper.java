package ec.edu.ups.icc.fundamentos01.categories.mapper;

import ec.edu.ups.icc.fundamentos01.categories.dtos.CategoriaResponseDto;
import ec.edu.ups.icc.fundamentos01.categories.dtos.CreateCategoriaDto;
import ec.edu.ups.icc.fundamentos01.categories.entity.CategoryEntity;


public class CategoriaMapper {

     public static CategoryEntity toEntity(CreateCategoriaDto dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }


    public static CategoriaResponseDto toDto(CategoryEntity entity) {
        CategoriaResponseDto dto = new CategoriaResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}
