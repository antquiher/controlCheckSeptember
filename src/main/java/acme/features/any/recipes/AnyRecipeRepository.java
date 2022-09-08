package acme.features.any.recipes;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.recipe.Recipe;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyRecipeRepository extends AbstractRepository {

	@Query("select r from Recipe r where r.isPublished = true")
	Collection<Recipe> findManyByPublised();
	

}
