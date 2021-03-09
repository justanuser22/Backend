package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository <Product, Long> {

    // Método 'Query' para matchear el ID deseado.
    // Esto es equivalente a: SELECT * FROM products where category_id=?
    // Endpoint: 'http://localhost:8080/api/products/search/findByCategoryId?id=1,2,3, etc'
    Page <Product> findByCategoryId (@RequestParam("id") Long id, Pageable pageable);

    // Método 'Query' para encontrar el/los producto/s que cohincidan con la búsqueda ingresada en el campo de texto.
    // Equivalente a: SELECT * FROM Product p WHERE p.name LIKE CONCAT('&', :name, '&')
    // Endpoint: 'http://localhost:8080/api/products/search/findByNameContaining?name=Python'
    Page <Product> findByNameContaining (@RequestParam("name") String name, Pageable pageable);
}
