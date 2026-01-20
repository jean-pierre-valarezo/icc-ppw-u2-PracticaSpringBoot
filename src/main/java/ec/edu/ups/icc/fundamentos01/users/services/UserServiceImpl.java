package ec.edu.ups.icc.fundamentos01.users.services;

import java.util.List;

import ec.edu.ups.icc.fundamentos01.exception.domain.NotFoundException;
import ec.edu.ups.icc.fundamentos01.products.dtos.ProductResponseDto;
import ec.edu.ups.icc.fundamentos01.products.mappers.ProductMapper;

import ec.edu.ups.icc.fundamentos01.products.repositories.ProductRepository;
import ec.edu.ups.icc.fundamentos01.users.dtos.*;
import ec.edu.ups.icc.fundamentos01.users.dtos.category.repository.UserRepository;
import ec.edu.ups.icc.fundamentos01.users.entities.UserEntity;
import ec.edu.ups.icc.fundamentos01.users.mappers.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;  

    private ProductRepository productRepository;

    public UserServiceImpl(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public Object findOne(int id) {
         UserEntity user = userRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponseDto create(CreateUserDto dto) {
        UserEntity user = UserMapper.toEntity(null, dto.name, dto.email);
        UserEntity saved = userRepository.save(user);
        return UserMapper.toResponse(saved);
    }

    @Override
    public Object update(int id, UpdateUserDto dto) {
        UserEntity user = userRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setName(dto.name);
        user.setEmail(dto.email);

        UserEntity saved = userRepository.save(user);
        return UserMapper.toResponse(saved);
    }

    @Override
    public Object partialUpdate(int id, PartialUpdateUserDto dto) {
        UserEntity user = userRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (dto.name != null) user.setName(dto.name);
        if (dto.email != null) user.setEmail(dto.email);

        UserEntity saved = userRepository.save(user);
        return UserMapper.toResponse(saved);
    }
    

    @Override
    public Object delete(int id) {
       if (!userRepository.existsById((long) id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        userRepository.deleteById((long) id);
        return id;
    }


    public List<ProductResponseDto> getProductsByUserId(Long userId) {

    if (!userRepository.existsById(userId)) {
        throw new NotFoundException("Usuario no encontrado");
    }

    return productRepository.findByOwnerId(userId)
        .stream()
        .map(ProductMapper::toDto)
        .toList();
}

@Override
public List<ProductResponseDto> getProductsByUserIdWithFilters(
        Long userId,
        String name,
        Double minPrice,
        Double maxPrice,
        Long categoryId) {

    if (!userRepository.existsById(userId)) {
        throw new NotFoundException("Usuario no encontrado");
    }

    return productRepository.findByOwnerIdWithFilters(
            userId, name, minPrice, maxPrice, categoryId)
        .stream()
        .map(ProductMapper::toDto)
        .toList();
}

}
