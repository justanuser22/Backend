package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
// El primer parámetro hace referencia a el nombre la entrada de los datos JSON y el segundo, el path '/products-categories'
@RepositoryRestResource(collectionResourceRel = "productCategory", path = "products-categories")
public interface ProductCategoryRepository extends JpaRepository <ProductCategory, Long> {
}
