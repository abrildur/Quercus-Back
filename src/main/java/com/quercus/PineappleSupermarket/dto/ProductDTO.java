package com.quercus.PineappleSupermarket.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class ProductDTO {

	private Long id;
	
	private String name;
	
	private String category;
	
	private String description;
	
	private int quantity;
	
	private float price;
	
	private String picture;

	public ProductDTO() {
		super();
	}

	public ProductDTO(Long id, String name, String category, String description, int quantity, float price,
			String picture) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.picture = picture;
	}
	
	public ProductDTO(String name, String category, String description, int quantity, float price,
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
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
