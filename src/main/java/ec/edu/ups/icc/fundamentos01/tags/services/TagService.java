package ec.edu.ups.icc.fundamentos01.tags.services;

import java.util.List;

import ec.edu.ups.icc.fundamentos01.tags.dtos.CreateTagDto;
import ec.edu.ups.icc.fundamentos01.tags.dtos.TagResponseDto;

public interface TagService {
    TagResponseDto create(CreateTagDto dto);
    List<TagResponseDto> findAll();
    void addTagToProduct(Long productId, Long tagId);
}
