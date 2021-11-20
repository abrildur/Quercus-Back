package com.quercus.PineappleSupermarket.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductModel {

	@JsonProperty("id")
	@NotNull
	private Long id;
	
	@JsonProperty("name")
	@NotNull
	private String name;
	
	@JsonProperty("category")
	@NotNull
	private String category;
	
	@JsonProperty("description")
	@NotNull
	private String description;
	
	@JsonProperty("quantity")
	@NotNull
	private int quantity;
	
	@JsonProperty("price")
	@NotNull
	private float price;
	
	@JsonProperty("picture")
	@NotNull
	private String picture;

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
