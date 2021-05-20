package br.com.compasso.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.compasso.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("select p from Product p where p.price >= :min_price and p.price <= :max_price")
	List<Product> findByMinMaxPrice(@Param("min_price") Double min_price, @Param("max_price") Double max_price);

	@Query("select p from Product p where p.price >= :min_price and p.price <= :max_price and p.name = :paramName and p.description = :paramDescription")
	List<Product> findBySearch(@Param("min_price") Double min_price, @Param("max_price") Double max_price, String paramName, String paramDescription);
	
}