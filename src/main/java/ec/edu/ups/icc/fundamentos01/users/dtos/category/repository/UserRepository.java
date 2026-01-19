package ec.edu.ups.icc.fundamentos01.users.dtos.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.ups.icc.fundamentos01.users.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
}
