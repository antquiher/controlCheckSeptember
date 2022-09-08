package acme.features.any.partOf;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.partOf.PartOf;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyPartOfRepository extends AbstractRepository {

	@Query("select p from PartOf p where p.recipe.isPublished = true and p.artifact.id=:id")
	Collection<PartOf> findManyRecipesByPublisedAndArtifactId(int id);
	
	@Query("select p from PartOf p where p.artifact.isPublished = true and p.recipe.id=:id")
	Collection<PartOf> findManyArtifactByPublisedAndRecipeId(int id);
	
}
