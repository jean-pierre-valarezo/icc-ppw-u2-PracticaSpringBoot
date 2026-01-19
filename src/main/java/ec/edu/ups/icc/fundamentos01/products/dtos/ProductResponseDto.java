package ec.edu.ups.icc.fundamentos01.products.dtos;

import java.time.LocalDateTime;
import java.util.List;

import ec.edu.ups.icc.fundamentos01.categories.dtos.CategoriaResponseDto;

public class ProductResponseDto {

    private Long id;
    private String name;
    private double price;
    private String description;

   
    private UserSummaryDto user;
    private List<CategoriaResponseDto> categories;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static class UserSummaryDto {
        public Long id;
        public String name;
        public String email;
    }

    public static class CategoriaSummaryDto {
        public Long id;
        public String name;

    }

     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserSummaryDto getUser() {
        return user;
    }

    public void setUser(UserSummaryDto user) {
        this.user = user;
    }

    public List<CategoriaResponseDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriaResponseDto> categories) {
        this.categories = categories;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
