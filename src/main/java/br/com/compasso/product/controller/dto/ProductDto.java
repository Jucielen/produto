package br.com.compasso.product.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.product.model.Product;

public class ProductDto {
	private Long id;
	private String name;
	private String description;
	private String price;
	
	public ProductDto() {
		
	} 
	public ProductDto(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = Double.toString(product.getPrice());
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public static List<ProductDto> converter(List<Product> products){
		List<ProductDto> lista = products.stream().map(ProductDto::new).collect(Collectors.toList());
		return lista;
	}
}
