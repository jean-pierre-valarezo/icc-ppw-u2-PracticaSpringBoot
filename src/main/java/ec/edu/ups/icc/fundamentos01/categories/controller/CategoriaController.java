package ec.edu.ups.icc.fundamentos01.categories.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.ups.icc.fundamentos01.categories.dtos.CategoriaResponseDto;
import ec.edu.ups.icc.fundamentos01.categories.dtos.CreateCategoriaDto;
import ec.edu.ups.icc.fundamentos01.categories.service.CategoriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }


    @PostMapping
    public CategoriaResponseDto create(@Valid @RequestBody CreateCategoriaDto dto) {
    return service.create(dto);
    }

    @GetMapping
    public List<CategoriaResponseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CategoriaResponseDto findOne(@PathVariable Long id) {
        return service.findOne(id);
    }
}
