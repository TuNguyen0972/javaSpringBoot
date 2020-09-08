package com.trungtamjava.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	@Column(name = "Name")
	private String name;
	@Column(name = "Price")
	private float price;
	@Column(name = "SoLuong")
	private int soLuong;
	@Column(name = "Description")
	private String description;
	@Column(name = "Image")
	private String image;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Product() {
	}

	public Product(int id, String name, float price, int soLuong, String description, String image,
			Category category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.soLuong = soLuong;
		this.description = description;
		this.image = image;
		this.category = category;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public float getPrice() {
		return price;
	}

	
	public void setPrice(float price) {
		this.price = price;
	}

	
	public int getSoLuong() {
		return soLuong;
	}

	
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
