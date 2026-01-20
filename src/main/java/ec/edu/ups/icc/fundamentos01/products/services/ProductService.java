package ec.edu.ups.icc.fundamentos01.products.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import ec.edu.ups.icc.fundamentos01.products.dtos.CreateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.PartialUpdateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.ProductResponseDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.UpdateProductDto;

public interface ProductService {

     List<ProductResponseDto> findAll();

    ProductResponseDto findOne(Long id);

    ProductResponseDto create(CreateProductDto dto);

    ProductResponseDto update(Long id, UpdateProductDto dto);

    ProductResponseDto partialUpdate(Long id, PartialUpdateProductDto dto);

   boolean validateName(Long id, String name);
    

    void delete(Long id);

    List<ProductResponseDto> findByCategoryName(String categoryName);


    Page<ProductResponseDto> findAll(int page, int size, String[] sort);
Slice<ProductResponseDto> findAllSlice(int page, int size, String[] sort);

Page<ProductResponseDto> findWithFilters(
    String name, Double minPrice, Double maxPrice, Long categoryId,
    int page, int size, String[] sort
);

Page<ProductResponseDto> findByUserIdWithFilters(
    Long userId, String name, Double minPrice, Double maxPrice, Long categoryId,
    int page, int size, String[] sort
);


}
