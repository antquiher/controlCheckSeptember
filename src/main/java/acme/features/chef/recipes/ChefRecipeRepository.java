package acme.features.chef.recipes;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.partOf.PartOf;
import acme.entities.recipe.Recipe;
import acme.entities.systemSetting.SystemSettings;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefRecipeRepository extends AbstractRepository {

	@Query("select r from Recipe r where r.chef.id=:id")
	Collection<Recipe> findManyByPublised(int id);

	@Query("select c from Chef c where c.id=:id")
	Chef findOneInventorByInventorId(int id);

	@Query("select p from PartOf p where p.recipe=:recipe")
	Collection<PartOf> findAllPartoOfByRecipe(Recipe recipe);
	
	@Query("select s from SystemSettings s")
	SystemSettings findAllSpanTuples();
	
	@Query("select r from Recipe r where r.code=:code")
	Optional<Recipe> findRecipeByCode(String code);
	
}
