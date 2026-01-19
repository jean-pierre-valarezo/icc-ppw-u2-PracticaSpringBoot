package ec.edu.ups.icc.fundamentos01.products.entities;


import java.util.HashSet;
import java.util.Set;

import ec.edu.ups.icc.fundamentos01.categories.entity.CategoryEntity;
import ec.edu.ups.icc.fundamentos01.core.entities.BaseModel;
import ec.edu.ups.icc.fundamentos01.users.entities.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseModel{
    
     @Column(nullable = false)
    public String name;
 
    @Column(nullable = false)
    public String description;

    @Column(nullable = false)
   public double price;

    
    
@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public UserEntity owner;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "product_categories",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryEntity> categories = new HashSet<>();

    public String getName() { return name; }
public void setName(String name) { this.name = name; }

public String getDescription() { return description; }
public void setDescription(String description) { this.description = description; }

public double getPrice() { return price; }
public void setPrice(double price) { this.price = price; }


public UserEntity getOwner() {
    return owner;
}
public void setOwner(UserEntity owner) {
    this.owner = owner;
}

public Set<CategoryEntity> getCategory() {
    return categories;
}

public void addCategory(CategoryEntity category) {
    categories.add(category);

}

public void removeCategory(CategoryEntity category) {
    this.categories.remove(category); 

}   

public void clearCategories() {
    categories.clear();

}
 public Set<CategoryEntity> getCategories() {
        return categories;
    }



}
