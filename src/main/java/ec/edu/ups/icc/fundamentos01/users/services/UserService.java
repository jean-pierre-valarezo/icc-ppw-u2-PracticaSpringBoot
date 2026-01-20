package ec.edu.ups.icc.fundamentos01.users.services;

import java.util.List;

import ec.edu.ups.icc.fundamentos01.products.dtos.ProductResponseDto;
import ec.edu.ups.icc.fundamentos01.users.dtos.CreateUserDto;
import ec.edu.ups.icc.fundamentos01.users.dtos.PartialUpdateUserDto;
import ec.edu.ups.icc.fundamentos01.users.dtos.UpdateUserDto;
import ec.edu.ups.icc.fundamentos01.users.dtos.UserResponseDto;

public interface UserService {

    List<UserResponseDto> findAll();

    Object findOne(int id);

    UserResponseDto create(CreateUserDto dto);

    Object update(int id, UpdateUserDto dto);

    Object partialUpdate(int id, PartialUpdateUserDto dto);

    Object delete(int id);

   List<ProductResponseDto> getProductsByUserIdWithFilters(
        Long userId,
        String name,
        Double minPrice,
        Double maxPrice,
        Long categoryId
    );

   List<ProductResponseDto> getProductsByUserId(Long id);

}