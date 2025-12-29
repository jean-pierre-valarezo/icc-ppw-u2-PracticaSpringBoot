package ec.edu.ups.icc.fundamentos01.products.services;

import java.util.List;
import org.springframework.stereotype.Service;
import ec.edu.ups.icc.fundamentos01.exception.domain.ConflictException;
import ec.edu.ups.icc.fundamentos01.exception.domain.NotFoundException;
import ec.edu.ups.icc.fundamentos01.products.dtos.*;
import ec.edu.ups.icc.fundamentos01.products.entities.ProductEntity;
import ec.edu.ups.icc.fundamentos01.products.mappers.ProductMapper;
import ec.edu.ups.icc.fundamentos01.products.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    @Override
    public ProductResponseDto findOne(Long id) {
        ProductEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Producto no encontrado con ID: " + id)
                );

        return ProductMapper.toDto(entity);
    }

    @Override
    public ProductResponseDto create(CreateProductDto dto) {

        
        if (repository.existsByName(dto.getName())) {
        throw new ConflictException(
            "Ya existe un producto con el nombre: " + dto.getName()
        );
    }

        ProductEntity entity = new ProductEntity();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setStock(0);
        entity.setDescription("Sin descripcion");

        repository.save(entity);
        return ProductMapper.toDto(entity);
    }

    @Override
    public ProductResponseDto update(Long id, UpdateProductDto dto) {
        ProductEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Producto no encontrado con ID: " + id)
                );

        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setStock(dto.getStock());

        repository.save(entity);
        return ProductMapper.toDto(entity);
    }

    @Override
    public ProductResponseDto partialUpdate(Long id, PartialUpdateProductDto dto) {
        ProductEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Producto no encontrado con ID: " + id)
                );

        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getPrice() != null) entity.setPrice(dto.getPrice());
        if (dto.getStock() != null) entity.setStock(dto.getStock());

        repository.save(entity);
        return ProductMapper.toDto(entity);
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
