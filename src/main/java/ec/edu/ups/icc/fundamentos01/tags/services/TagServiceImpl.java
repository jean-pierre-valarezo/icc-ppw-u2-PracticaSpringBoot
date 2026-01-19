package ec.edu.ups.icc.fundamentos01.tags.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ec.edu.ups.icc.fundamentos01.exception.domain.NotFoundException;
import ec.edu.ups.icc.fundamentos01.products.entities.ProductEntity;
import ec.edu.ups.icc.fundamentos01.products.repositories.ProductRepository;
import ec.edu.ups.icc.fundamentos01.tags.dtos.CreateTagDto;
import ec.edu.ups.icc.fundamentos01.tags.dtos.TagResponseDto;
import ec.edu.ups.icc.fundamentos01.tags.entities.TagEntity;
import ec.edu.ups.icc.fundamentos01.tags.mappers.TagMapper;
import ec.edu.ups.icc.fundamentos01.tags.repository.TagRepository;
import jakarta.transaction.Transactional;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepo;
    private final ProductRepository productRepo;

    public TagServiceImpl(TagRepository tagRepo, ProductRepository productRepo) {
        this.tagRepo = tagRepo;
        this.productRepo = productRepo;
    }

    @Override
    public TagResponseDto create(CreateTagDto dto) {
        TagEntity tag = new TagEntity();
        tag.setName(dto.name);
        return TagMapper.toDto(tagRepo.save(tag));
    }

    @Override
    public List<TagResponseDto> findAll() {
        return tagRepo.findAll().stream().map(TagMapper::toDto).toList();
    }

    @Override
    @Transactional
    public void addTagToProduct(Long productId, Long tagId) {

        ProductEntity product = productRepo.findById(productId)
            .orElseThrow(() -> new NotFoundException("Producto no encontrado con ID: " + productId));

        TagEntity tag = tagRepo.findById(tagId)
            .orElseThrow(() -> new NotFoundException("Tag no encontrado con ID: " + tagId));

        
        tag.getProducts().add(product);
        tagRepo.save(tag);
    }
}
