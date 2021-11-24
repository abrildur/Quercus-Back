package com.quercus.PineappleSupermarket.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "category")
public class Category {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String name;

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Product> products = new ArrayList<>();
    
    
	public Category() {
	}
	
	public Category(String name) {
		this.name = name;
	}

	public Category(Long id, String name, List<Product> products) {
		super();
		this.name = name;
		this.products = products;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProducts(Product product) {
		product.setCategory(this);
		this.products.add(product);
	}
	
	public void deleteProduct(Product product) {
		this.products.remove(product);
	}
}
