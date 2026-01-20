package ec.edu.ups.icc.fundamentos01.users;

import ec.edu.ups.icc.fundamentos01.products.dtos.ProductResponseDto;
import ec.edu.ups.icc.fundamentos01.users.dtos.CreateUserDto;
import ec.edu.ups.icc.fundamentos01.users.dtos.PartialUpdateUserDto;
import ec.edu.ups.icc.fundamentos01.users.dtos.UpdateUserDto;
import ec.edu.ups.icc.fundamentos01.users.dtos.UserResponseDto;
import ec.edu.ups.icc.fundamentos01.users.services.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UserService service;

    public UsersController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserResponseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Object findOne(@PathVariable int id) {
        return service.findOne(id);
    }

    @PostMapping
    public UserResponseDto create(@RequestBody CreateUserDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable int id, @RequestBody UpdateUserDto dto) {
        return service.update(id, dto);
    }

    @PatchMapping("/{id}")
    public Object partialUpdate(@PathVariable int id, @RequestBody PartialUpdateUserDto dto) {
        return service.partialUpdate(id, dto);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable int id) {
        return service.delete(id);
    }
   @GetMapping("/{id}/products")
public List<ProductResponseDto> getProducts(@PathVariable Long id) {
    return service.getProductsByUserId(id);
}

@GetMapping("/{id}/products-v2")
public List<ProductResponseDto> getProductsWithFilters(
        @PathVariable Long id,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) Double minPrice,
        @RequestParam(required = false) Double maxPrice,
        @RequestParam(required = false) Long categoryId) {

    return service.getProductsByUserIdWithFilters(
        id, name, minPrice, maxPrice, categoryId);
}


}

