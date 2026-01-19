package ec.edu.ups.icc.fundamentos01.products.dtos;

import java.util.List;

import jakarta.validation.constraints.Positive;

public class PartialUpdateProductDto {

    public String name;

    @Positive(message = "El precio debe ser mayor a 0")
    public Double price;


    public String description;

    public List<Long> categoryIds;

   

}
