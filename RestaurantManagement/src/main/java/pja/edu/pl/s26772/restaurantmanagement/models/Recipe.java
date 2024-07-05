package pja.edu.pl.s26772.restaurantmanagement.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Recipe {
    @EmbeddedId
    private RecipePK id;
    private String name;
    private double quantity;

    public Recipe() {
    }

    public RecipePK getId() {
        return id;
    }

    public void setId(RecipePK id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Embeddable
    public static class RecipePK implements Serializable{
        @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
        private Product product;
        @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
        private MenuItem menuItem;

        public RecipePK() {
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public MenuItem getMenuItem() {
            return menuItem;
        }

        public void setMenuItem(MenuItem menuItem) {
            this.menuItem = menuItem;
        }
    }
}

