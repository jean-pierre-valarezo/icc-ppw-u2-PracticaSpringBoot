package ec.edu.ups.icc.fundamentos01.tags.mappers;

import ec.edu.ups.icc.fundamentos01.tags.dtos.TagResponseDto;
import ec.edu.ups.icc.fundamentos01.tags.entities.TagEntity;

public class TagMapper {
    public static TagResponseDto toDto(TagEntity e) {
        TagResponseDto dto = new TagResponseDto();
        dto.id = e.getId();
        dto.name = e.getName();
        return dto;
    }
}