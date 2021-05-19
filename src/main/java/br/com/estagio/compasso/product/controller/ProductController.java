package br.com.estagio.compasso.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estagio.compasso.product.controller.dto.ProductDto;
import br.com.estagio.compasso.product.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	ProductRepository repository;
	
	@GetMapping
	public List<ProductDto> lista(){
		List<Produto> topicos = topicoRepository.findAll(paginacao);
		
	}
}
