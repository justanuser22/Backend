package com.luv2code.ecommerce.config;

import com.luv2code.ecommerce.entities.Country;
import com.luv2code.ecommerce.entities.Product;
import com.luv2code.ecommerce.entities.ProductCategory;
import com.luv2code.ecommerce.entities.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        // deshabilitando los métodos HTTP para la clase 'Producto': POST, PUT y DELETE.
        disableHttpMethods(Product.class, config, theUnsupportedActions);

        // deshabilitando los métodos HTTP para la clase 'ProductCategory': POST, PUT y DELETE.
        disableHttpMethods(ProductCategory.class, config, theUnsupportedActions);

        // deshabilitando los métodos HTTP para las clases 'Country' y 'State': POST, PUT y DELETE.
        disableHttpMethods(Country.class, config, theUnsupportedActions);
        disableHttpMethods(State.class, config, theUnsupportedActions);

        // llamando a un método ayudante interno para exponer los ID's de la clase 'products-categories',
        // con la finalidad de poder hacer una búsqueda de las categorías de los productos más dinámica
        exposeIds (config);
    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {

        // exponiendo las ID's de las entidades
        // obteniendo la lista de las clases entidades desde EntityManager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        // crear una matriz de los tipos de entidad
        List<Class> entityClass = new ArrayList<>();
        // obtener los tipos de entidad para las entidades
        for (EntityType tempEntityType : entities) {
           entityClass.add(tempEntityType.getJavaType());
        }
        // exponer los identificadores de entidad para la matriz de tipos de entidad / dominio
        Class[] domainTypes = entityClass.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
