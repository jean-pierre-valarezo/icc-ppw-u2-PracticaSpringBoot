package ec.edu.ups.icc.fundamentos01.tags.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.ups.icc.fundamentos01.tags.entities.TagEntity;

public interface TagRepository extends JpaRepository<TagEntity, Long>{
   
    Optional<TagEntity> findByName(String name);

    boolean existsByName(String name);
}
