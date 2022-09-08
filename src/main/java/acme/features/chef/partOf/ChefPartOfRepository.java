package acme.features.chef.partOf;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artifact.Artifact;
import acme.entities.partOf.PartOf;
import acme.entities.recipe.Recipe;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefPartOfRepository extends AbstractRepository {

	@Query("select p from PartOf p where p.recipe.isPublished = true and p.artifact.id=:id")
	Collection<PartOf> findManyRecipesByPublisedAndArtifactId(int id);
	
	@Query("select p from PartOf p where p.artifact.isPublished = true and p.recipe.id=:id")
	Collection<PartOf> findManyArtifactByPublisedAndRecipeId(int id);

	@Query("select r from Recipe r where r.id=:id")
	Recipe findOnePartOfByRecipe(int id);
	
	@Query("select p.artifact from PartOf p where p.artifact.isPublished = true and p.recipe.id=:id")
	Collection<Artifact> findManyArtifactsByPublisedAndRecipeId(int id);
	
	@Query("select a from Artifact a where a.isPublished = true")
	Collection<Artifact> findAllPublishedArtifacts();

	@Query("select a from Artifact a where a.id=:artifactId")
	Artifact findArtifactById(Integer artifactId);
}
