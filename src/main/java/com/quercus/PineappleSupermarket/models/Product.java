package com.quercus.PineappleSupermarket.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Product {
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
	
	@Column(length = 20)
	private String name;
	
	// Aca hacer el mapeo con category -> OneToMany/ManyToOne
	private Category category;
	
	private String description;
	private int quantity;
	private float price;
	
	private String picture;

	public Product() {
		super();
	}

	public Product(String name, Category category, String description, int quantity, float price, String picture) {
		super();
		this.name = name;
		this.category = category;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
