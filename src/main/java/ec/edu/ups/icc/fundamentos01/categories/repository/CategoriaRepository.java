package ec.edu.ups.icc.fundamentos01.categories.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.ups.icc.fundamentos01.categories.entity.CategoryEntity;

public interface CategoriaRepository  extends JpaRepository<CategoryEntity, Long> {
    
    boolean existsByName(String name);

    Optional<CategoryEntity> findByName(String name);

}
