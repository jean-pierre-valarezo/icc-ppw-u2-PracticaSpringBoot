package ec.edu.ups.icc.fundamentos01.tags.entities;

import java.util.HashSet;
import java.util.Set;

import ec.edu.ups.icc.fundamentos01.core.entities.BaseModel;
import ec.edu.ups.icc.fundamentos01.products.entities.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tags")
public class TagEntity extends BaseModel{
    @Column(nullable = false, unique = true, length = 80)
    private String name;

    @ManyToMany
    @JoinTable(
        name = "product_tags",
        joinColumns = @JoinColumn(name = "tag_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ProductEntity> products = new HashSet<>();

    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name;
     }

    public Set<ProductEntity> getProducts() { 
        return products;
     }

    public void setProducts(Set<ProductEntity> products) { 
        this.products = products; 
    }
}
