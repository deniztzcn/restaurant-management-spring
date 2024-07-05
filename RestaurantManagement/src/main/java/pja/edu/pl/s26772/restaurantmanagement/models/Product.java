package pja.edu.pl.s26772.restaurantmanagement.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String measurement;
    @OneToMany(mappedBy = "id.product",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Recipe> recipes;
    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private List<SupplyProduct> supplyProducts;

    public Product() {
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<SupplyProduct> getSupplyProducts() {
        return supplyProducts;
    }

    public void setSupplyProducts(List<SupplyProduct> supplyProducts) {
        this.supplyProducts = supplyProducts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
}
