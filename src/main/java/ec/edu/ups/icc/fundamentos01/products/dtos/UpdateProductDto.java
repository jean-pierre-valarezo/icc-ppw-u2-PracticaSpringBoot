package ec.edu.ups.icc.fundamentos01.products.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class UpdateProductDto {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 150)
    private String name;

    @NotNull
    @Positive(message = "El precio debe ser mayor a 0")
    private double price;


    @NotNull
    private String description;


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
}
