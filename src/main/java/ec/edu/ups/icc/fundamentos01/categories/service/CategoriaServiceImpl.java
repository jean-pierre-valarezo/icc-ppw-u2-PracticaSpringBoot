package ec.edu.ups.icc.fundamentos01.categories.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ec.edu.ups.icc.fundamentos01.categories.dtos.CategoriaResponseDto;
import ec.edu.ups.icc.fundamentos01.categories.dtos.CreateCategoriaDto;
import ec.edu.ups.icc.fundamentos01.categories.entity.CategoryEntity;
import ec.edu.ups.icc.fundamentos01.categories.mapper.CategoriaMapper;
import ec.edu.ups.icc.fundamentos01.categories.repository.CategoriaRepository;
import ec.edu.ups.icc.fundamentos01.exception.domain.ConflictException;


@Service
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaServiceImpl(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public CategoriaResponseDto create(CreateCategoriaDto dto) {

        if (repository.existsByName(dto.getName())) {
            throw new ConflictException(
                "Ya existe una categoría con el nombre: " + dto.getName()
            );
        }

        CategoryEntity entity = CategoriaMapper.toEntity(dto);
        CategoryEntity saved = repository.save(entity);

        return CategoriaMapper.toDto(saved);
    }

    @Override
    public List<CategoriaResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(CategoriaMapper::toDto)
                .toList();
    }

    @Override
    public CategoriaResponseDto findOne(Long id) {
        CategoryEntity entity = repository.findById(id).orElseThrow(() ->
            new ConflictException("Categoría no encontrada con id: " + id)
        );
        return CategoriaMapper.toDto(entity);
    }
        
}
