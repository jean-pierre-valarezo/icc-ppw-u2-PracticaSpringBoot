package ec.edu.ups.icc.fundamentos01.tags.dtos;

import jakarta.validation.constraints.NotNull;

public class AddTagToProductDto {
    @NotNull
    public Long productId;

    @NotNull
    public Long tagId;
}
