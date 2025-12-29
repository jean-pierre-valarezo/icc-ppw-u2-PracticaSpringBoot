package ec.edu.ups.icc.fundamentos01.products.models;

import ec.edu.ups.icc.fundamentos01.products.dtos.CreateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.PartialUpdateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.UpdateProductDto;

public class Product {

    private int id;
    private String name;
    private double price;

    private Product(int id, String name, double price) {
        validate(name, price);
        this.id = id;
        this.name = name;
        this.price = price;
    }



    public static Product fromDto(CreateProductDto dto) {
        return new Product(0, dto.getName(), dto.getPrice());
    }

    public static Product fromEntity(int id, String name, double price) {
        return new Product(id, name, price);
    }

 

    public Product update(UpdateProductDto dto) {
        validate(dto.getName(), dto.getPrice());
        this.name = dto.getName();
        this.price = dto.getPrice();
        return this;
    }

    public Product partialUpdate(PartialUpdateProductDto dto) {
        if (dto.getName() != null && !dto.getName().isBlank()) {
            this.name = dto.getName();
        }
        if (dto.getPrice() != null && dto.getPrice() >= 0) {
            this.price = dto.getPrice();
        }
        return this;
    }

   

    private static void validate(String name, double price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price must be >= 0");
        }
    }

   

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
