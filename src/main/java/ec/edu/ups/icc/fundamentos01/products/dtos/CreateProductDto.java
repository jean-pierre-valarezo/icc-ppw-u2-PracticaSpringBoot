package ec.edu.ups.icc.fundamentos01.products.dtos;

import java.util.List;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateProductDto {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 150)
    public String name;

     @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false)
    public Double price;


    public List<Long> categoryIds; //[1,2]


    @NotNull(message = "El ID del usuario es obligatorio")
    public Long userId;

     @Size(max = 500)
    public String description;


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


    public List<Long> getCategoryIds() {
        return categoryIds;
    }       

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
     public String getDescription() { 
        return description; }


    public void setDescription(String description) { 
        this.description = description; }
}
