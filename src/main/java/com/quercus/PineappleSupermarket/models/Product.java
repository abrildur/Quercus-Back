package com.quercus.PineappleSupermarket.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="product")
public class Product {
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
	
	@Column(length = 128, nullable = false, unique = true)
	private String name;
	
    @ManyToOne
    @JoinColumn(name = "category_id")
	private Category category;
	
	private String description;
	
	@NotNull
	private int quantity;
	
	@NotNull
	private float price;
	
	@NotNull
	private String picture;

	
	public Product() {
	}

	public Product(String name, Category category, String description, int quantity, float price,
			String picture) {
		super();
		this.name = name;
		this.category = category;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.picture = picture;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
}
