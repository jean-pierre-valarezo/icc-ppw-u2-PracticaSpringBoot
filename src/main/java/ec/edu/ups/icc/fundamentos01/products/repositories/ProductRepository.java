package ec.edu.ups.icc.fundamentos01.products.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ec.edu.ups.icc.fundamentos01.products.dtos.ProductResponseDto;
import ec.edu.ups.icc.fundamentos01.products.entities.ProductEntity;



@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    boolean existsByName(String name);

    Optional<ProductEntity> findByName(String name);

    List<ProductEntity> findByOwnerId(Long userId);

    List<ProductEntity> findByCategoriesId(Long categoryId);

    List<ProductEntity> findByOwnerName(String name);

    List<ProductEntity> findByCategoriesName(String categoryName);

    List<ProductEntity> findByCategoriesIdAndPriceGreaterThan(Long categoryId, double price);

     List<ProductResponseDto> getProductsByUserId(Long userId);


   @Query("""
    SELECT DISTINCT p FROM ProductEntity p
    LEFT JOIN p.categories c
    WHERE (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
    AND (:minPrice IS NULL OR p.price >= :minPrice)
    AND (:maxPrice IS NULL OR p.price <= :maxPrice)
    AND (:categoryId IS NULL OR c.id = :categoryId)
""")
Page<ProductEntity> findWithFilters(
    String name,
    Double minPrice,
    Double maxPrice,
    Long categoryId,
    Pageable pageable
);

@Query("""
    SELECT DISTINCT p FROM ProductEntity p
    LEFT JOIN p.categories c
    WHERE p.owner.id = :userId
    AND (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
    AND (:minPrice IS NULL OR p.price >= :minPrice)
    AND (:maxPrice IS NULL OR p.price <= :maxPrice)
    AND (:categoryId IS NULL OR c.id = :categoryId)
""")
    Page<ProductEntity> findByUserIdWithFilters(
    Long userId,
    String name,
    Double minPrice,
    Double maxPrice,
    Long categoryId,
    Pageable pageable
);

}
