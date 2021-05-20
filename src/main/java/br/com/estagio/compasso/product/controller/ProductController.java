package br.com.estagio.compasso.product.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.estagio.compasso.product.controller.dto.ProductDto;
import br.com.estagio.compasso.product.controller.form.ProductForm;
import br.com.estagio.compasso.product.model.Product;
import br.com.estagio.compasso.product.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	ProductRepository repository;
	
	@GetMapping
	public List<ProductDto> lista(){
		List<Product> produtos = repository.findAll();
		return ProductDto.converter(produtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> detalhar(@PathVariable Long id){
		Optional <Product> prod = repository.findById(id);
		if(prod.isPresent()) {
			return ResponseEntity.ok(new ProductDto(prod.get()));
		}
		return ResponseEntity.notFound().build();
		
	}

	@GetMapping("/search")
	public List<ProductDto> listaFiltrada(@RequestParam(required = false) String q, @RequestParam(required = false) Double min_price, @RequestParam(required = false) Double max_price){
		
		List<ProductDto> lista = null;
		
		if(min_price == null) {
			min_price= 0.0;
		}
		if(max_price == null) {
			max_price= 10000.0;
		}
		if (q==null) {
			List<Product> produtos = repository.findByMinMaxPrice(min_price, max_price);
			lista = ProductDto.converter(produtos);
		}else {
			// preenchimento da q="name=exemplo,description=exemplo"
			String parametros = q;
			String[] split = parametros.split(","); 
			String[] paramName = split[0].split("=");
			String name = paramName[1];
			String[] paramDescription = split[1].split("=");
			String description = paramDescription[1].replace("\"", "");
			System.out.println(name+" "+description);		
			List<Product> produtos = repository.findBySearch(min_price, max_price, name, description);
			lista = ProductDto.converter(produtos);
		}
		return lista;
	}
	@PostMapping
	public ResponseEntity<?> newProduct(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder, BindingResult result){
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		
		Product prod = form.converter();
		repository.save(prod);
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(prod.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(prod));
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid ProductForm form){
		Optional<Product> optional = repository.findById(id);
		if(optional.isPresent()) {
			Product prod = form.atualizar(id, repository);
			return ResponseEntity.ok().body(new ProductDto(prod));
		}
		return ResponseEntity.notFound().build();
		
	}
}
