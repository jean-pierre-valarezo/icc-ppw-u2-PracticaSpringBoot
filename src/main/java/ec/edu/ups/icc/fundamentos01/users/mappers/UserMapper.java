package ec.edu.ups.icc.fundamentos01.users.mappers;

import ec.edu.ups.icc.fundamentos01.users.dtos.UserResponseDto;
import ec.edu.ups.icc.fundamentos01.users.entities.UserEntity;



public class UserMapper {
    public static UserEntity toEntity(Long id, String name, String email) {
         UserEntity user = new UserEntity();   
        user.setName(name);
        user.setEmail(email);
        user.setPassword("secret");          
        return user;
    }

    public static UserResponseDto toResponse(UserEntity user) {
        UserResponseDto dto = new UserResponseDto();
        dto.id = user.getId();
        dto.name = user.getName();
        dto.email = user.getEmail();
        return dto;
    }
}
