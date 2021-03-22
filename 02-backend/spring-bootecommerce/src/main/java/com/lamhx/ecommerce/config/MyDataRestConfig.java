package com.lamhx.ecommerce.config;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

import com.lamhx.ecommerce.entity.Product;
import com.lamhx.ecommerce.entity.ProductCategory;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer{

	private EntityManager entityManager;
	
	@Autowired
	public MyDataRestConfig(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
		
		// disable HTTP methods for Product: PUT, POST and DELETE
		config.getExposureConfiguration()
			  .forDomainType(Product.class)
			  .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
			  .withCollectionExposure((metdate, httpMethods) -> httpMethods.disable(theUnsupportedActions));
		
		// disable HTTP methods for ProductCategory: PUT, POST and DELETE
				config.getExposureConfiguration()
					  .forDomainType(ProductCategory.class)
					  .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
					  .withCollectionExposure((metdate, httpMethods) -> httpMethods.disable(theUnsupportedActions));
		
			// call an internal helper method
			exposeIds(config); 
	
	}


	
	private void exposeIds(RepositoryRestConfiguration config) {
		// expose entity ids
		//
		
		// - get a list of call all entity classes from the entity manager
		Set<EntityType<?>> entitis = entityManager.getMetamodel().getEntities();
		
		// - create an array of the entity types
		@SuppressWarnings("rawtypes")
		List<Class> entityClasses = new ArrayList<>();
		
		// - get the entity ids for the array of entity/domain types
		for(EntityType<?> tempEntityType : entitis) {
			entityClasses.add(tempEntityType.getJavaType());
		}
		
		// - expose the entity ids for the array of entity/domain types
		@SuppressWarnings("rawtypes")
		Class[] domainTypes = entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTypes);
	}
}
