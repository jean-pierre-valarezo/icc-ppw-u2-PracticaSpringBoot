package ec.edu.ups.icc.fundamentos01.tags.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateTagDto {
     @NotBlank
    @Size(min = 2, max = 80)
    public String name;
}
