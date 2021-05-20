package br.com.estagio.compasso.product.controller.form;

import javax.validation.constraints.NotBlank;

import br.com.estagio.compasso.product.model.Product;
import br.com.estagio.compasso.product.repository.ProductRepository;

public class ProductForm {
	@NotBlank
	private String name;
	@NotBlank
	private String description;
	@NotBlank
	private String price;
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
	
	public Product converter() {
		Double pricedouble =Double.valueOf(price);
		return new Product(name, description, pricedouble);
	}
	public Product atualizar(Long id, ProductRepository repository) {
		Product prod = repository.getOne(id);
		prod.setName(this.name);
		prod.setDescription(this.description);
		prod.setPrice(Double.valueOf(this.price));
		repository.save(prod);
		return prod;
	}
}
