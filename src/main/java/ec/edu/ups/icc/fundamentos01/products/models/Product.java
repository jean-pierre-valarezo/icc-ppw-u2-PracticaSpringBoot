package ec.edu.ups.icc.fundamentos01.products.models;

import ec.edu.ups.icc.fundamentos01.products.dtos.CreateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.PartialUpdateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.UpdateProductDto;
import ec.edu.ups.icc.fundamentos01.products.entities.ProductEntity;
import ec.edu.ups.icc.fundamentos01.users.entities.UserEntity;

public class Product {

    private Long id;
    private String name;
    private double price;
    private String description;

    public Product( String name, double price, String description) {
        validate(name, price,description);
        
        this.name = name;
        this.description = description;
        this.price = price;
    }
       

    public static Product fromDto(CreateProductDto dto) {
        return new Product(dto.getName(), dto.getPrice(), dto.getDescription());
    }

    public static Product fromEntity(int id, String name, double price, String description) {
        return new Product(name, price, description);
    }

    @SuppressWarnings("unchecked")
    public ProductEntity toEntity(UserEntity owner) {
         ProductEntity entity = new ProductEntity();
        entity.setId(id);
        entity.setName(name);
        entity.setPrice(price);
        entity.setDescription(description);
        entity.setOwner(owner);
        return entity;
    }

    public Product update(UpdateProductDto dto) {
        validate(dto.getName(), dto.getPrice(), dto.getDescription());
    this.name = dto.getName();
    this.price = dto.getPrice();
    this.description = dto.getDescription();
    return this;
    }

    public Product partialUpdate(PartialUpdateProductDto dto) {
       if (dto.name != null) this.name = dto.name;
    if (dto.price != null) this.price = dto.price;
    if (dto.description != null) this.description = dto.description;
    return this;
    }

   

    private static void validate(String name, double price, String description) {
        if (name == null || name.isBlank()) {
        throw new IllegalArgumentException("Product name is required");
    }
    if (description == null || description.isBlank()) {
        throw new IllegalArgumentException("Product description is required");
    }
    if (price < 0) {
        throw new IllegalArgumentException("Price must be >= 0");
    }
    }

    
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
