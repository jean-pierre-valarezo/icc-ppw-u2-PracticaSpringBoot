package ec.edu.ups.icc.fundamentos01.products.services;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import ec.edu.ups.icc.fundamentos01.categories.dtos.CategoriaResponseDto;
import ec.edu.ups.icc.fundamentos01.categories.entity.CategoryEntity;
import ec.edu.ups.icc.fundamentos01.categories.repository.CategoriaRepository;

import ec.edu.ups.icc.fundamentos01.exception.domain.ConflictException;
import ec.edu.ups.icc.fundamentos01.exception.domain.NotFoundException;
import ec.edu.ups.icc.fundamentos01.products.dtos.*;
import ec.edu.ups.icc.fundamentos01.products.entities.ProductEntity;
import ec.edu.ups.icc.fundamentos01.products.mappers.ProductMapper;
import ec.edu.ups.icc.fundamentos01.products.models.Product;
import ec.edu.ups.icc.fundamentos01.products.repositories.ProductRepository;
import ec.edu.ups.icc.fundamentos01.users.dtos.category.repository.UserRepository;
import ec.edu.ups.icc.fundamentos01.users.entities.UserEntity;



@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private final UserRepository userRepo;

    private final CategoriaRepository categoryRepository;

    public ProductServiceImpl(ProductRepository repository, UserRepository userRepo, CategoriaRepository categoryRepository) {
        this.repository = repository;
        this.userRepo = userRepo;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductResponseDto> findAll() {
        return repository.findAll()
            .stream()
            .map(this::toResponseDto) 
            .toList();
    }

    @Override
    public ProductResponseDto findOne(Long id) {
        ProductEntity entity = repository.findById(id)
            .orElseThrow(() ->
                    new NotFoundException("Producto no encontrado con ID: " + id)
            );

    return toResponseDto(entity);
    }

    @Override
    public ProductResponseDto create(CreateProductDto dto) {

     UserEntity owner = userRepo.findById(dto.userId)
            .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

    Set<CategoryEntity> categories = validateAndGetCategories(dto.categoryIds);

    Product product = Product.fromDto(dto);
    ProductEntity entity = product.toEntity(owner);

    entity.getCategories().addAll(categories);

    return toResponseDto(repository.save(entity));
 }
     private Set<CategoryEntity> validateAndGetCategories(List<Long> categoryIds) {
        Set<CategoryEntity> categories = new HashSet<>();
    
    for (Long categoryId : categoryIds) {
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada: " + categoryId));
        categories.add(category);
    }
    
    return categories;    
}

     private ProductResponseDto toResponseDto(ProductEntity entity) {
         ProductResponseDto dto = new ProductResponseDto();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setPrice(entity.getPrice());
    dto.setDescription(entity.getDescription());

    ProductResponseDto.UserSummaryDto userDto = new ProductResponseDto.UserSummaryDto();
    userDto.id = entity.getOwner().getId();
    userDto.name = entity.getOwner().getName();
    userDto.email = entity.getOwner().getEmail();
    dto.setUser(userDto);

    List<CategoriaResponseDto> categories = entity.getCategories().stream().map(cat -> {
        CategoriaResponseDto c = new CategoriaResponseDto();
        c.setId(cat.getId());
        c.setName(cat.getName());
        c.setDescription(cat.getDescription());
        return c;
    }).toList();

    dto.setCategories(categories);

    dto.setCreatedAt(entity.getCreatedAt());
    dto.setUpdatedAt(entity.getUpdatedAt());

    return dto;
    }
    

    @Override
    public ProductResponseDto update(Long id, UpdateProductDto dto) {
        ProductEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Producto no encontrado con ID: " + id)
                );

        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
      

        repository.save(entity);
        return ProductMapper.toDto(entity);
    }

    @Override
    public ProductResponseDto partialUpdate(Long id, PartialUpdateProductDto dto) {
        ProductEntity entity = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

    Product product = new Product(
        entity.getName(),
        entity.getPrice(),
        entity.getDescription()
    );

    product.partialUpdate(dto);

    entity.setName(product.getName());
    entity.setPrice(product.getPrice());
    entity.setDescription(product.getDescription());

    if (dto.categoryIds != null) {
        Set<CategoryEntity> categories = validateAndGetCategories(dto.categoryIds);
        entity.clearCategories();
        entity.getCategories().addAll(categories);
    }

    return toResponseDto(repository.save(entity));
    }


    
    @Override
    public List<ProductResponseDto> findByCategoryName(String categoryName) {
        return repository.findByCategoriesName(categoryName)
            .stream()
            .map(this::toResponseDto) 
            .toList();
    }

    @Override
    public boolean validateName(Long id, String name) {
        repository.findByName(name).ifPresent(existing -> {

        if (id == null || !existing.getId().equals(id)) {
            throw new ConflictException(
                "Ya existe un producto con el nombre: " + name
            );
        }
    });
        return true;

    }

    @Override
    public void delete(Long id) {
         ProductEntity entity = repository.findById(id)
        .orElseThrow(() ->
            new NotFoundException("Producto no encontrado con ID: " + id)
        );

    repository.delete(entity);
    }
}



