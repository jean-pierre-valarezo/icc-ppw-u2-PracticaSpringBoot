package ec.edu.ups.icc.fundamentos01.tags.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.ups.icc.fundamentos01.tags.dtos.AddTagToProductDto;
import ec.edu.ups.icc.fundamentos01.tags.dtos.CreateTagDto;
import ec.edu.ups.icc.fundamentos01.tags.dtos.TagResponseDto;
import ec.edu.ups.icc.fundamentos01.tags.services.TagService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final TagService service;

    public TagController(TagService service) {
        this.service = service;
    }

    @PostMapping
    public TagResponseDto create(@Valid @RequestBody CreateTagDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<TagResponseDto> findAll() {
        return service.findAll();
    }

    @PostMapping("/assign")
    public void addTagToProduct(@Valid @RequestBody AddTagToProductDto dto) {
        service.addTagToProduct(dto.productId, dto.tagId);
    }
}
