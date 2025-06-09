package com.example.demo.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "Products")
public class Product 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String image;

    @Column(name = "stock_quantity")
    private Integer stock;
    private String category;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    public Product() {}

    public Product (String name, String description, Double price, String image, Integer stock, String category)
    {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.stock = stock;
        this.category = category;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public Integer getStock()
    {
        return stock;
    }

    public void setStock(Integer stock)
    {
        this.stock = stock;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public LocalDateTime getCreated_at() 
    {
    return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) 
    {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() 
    {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) 
    {
        this.updated_at = updated_at;
    }

}
