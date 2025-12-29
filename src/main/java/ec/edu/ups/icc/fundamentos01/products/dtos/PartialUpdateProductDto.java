package ec.edu.ups.icc.fundamentos01.products.dtos;

import jakarta.validation.constraints.Positive;

public class PartialUpdateProductDto {

    private String name;

    @Positive(message = "El precio debe ser mayor a 0")
    private Double price;

    @Positive(message = "El stock debe ser mayor a 0")
    private Integer stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
