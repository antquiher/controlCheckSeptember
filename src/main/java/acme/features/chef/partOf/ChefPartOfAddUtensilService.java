package acme.features.chef.partOf;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artifact.Artifact;
import acme.entities.artifact.ArtifactType;
import acme.entities.partOf.PartOf;
import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefPartOfAddUtensilService implements AbstractCreateService<Chef, PartOf> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefPartOfRepository repository;

	// AbstractCreateService<Chef, Recipe> interface --------------


	@Override
	public boolean authorise(final Request<PartOf> request) {
		assert request != null;
		Recipe recipe;

		final int masterId = request.getModel().getInteger("masterId");
		recipe = this.repository.findOnePartOfByRecipe(masterId);
		return !recipe.isPublished();
	}

	@Override
	public PartOf instantiate(final Request<PartOf> request) {
		assert request != null;

		PartOf result;
		Recipe recipe;

		final int masterId = request.getModel().getInteger("masterId");
		recipe = this.repository.findOnePartOfByRecipe(masterId);
		result = new PartOf();
		
		result.setRecipe(recipe);
		result.setQuantity(1.);
		
		return result;
	}

	@Override
	public void bind(final Request<PartOf> request, final PartOf entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
		
		final String artifactIdString = ((String) request.getModel().getAttribute("artifact"));
		if(artifactIdString != null && !artifactIdString.isEmpty()) {
			final Integer artifactId = Integer.parseInt(artifactIdString);
			final Artifact artifact = this.repository.findArtifactById(artifactId);
			entity.setArtifact(artifact);
		}
		
	}

	@Override
	public void validate(final Request<PartOf> request, final PartOf entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void unbind(final Request<PartOf> request, final PartOf entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);
		final Collection<Artifact> artifacts = this.repository.findAllPublishedArtifacts();
		Collection<Artifact> ca = repository.findManyArtifactsByPublisedAndRecipeId(entity.getRecipe().getId());
		model.setAttribute("artifacts", artifacts.stream().filter(x->!ca.contains(x)).filter(y->y.getType().equals(ArtifactType.UTENSIL)).collect(Collectors.toList()));
		final int masterId = request.getModel().getInteger("masterId");
		model.setAttribute("masterId", masterId);
		model.setAttribute("published", entity.getRecipe().isPublished());
		model.setAttribute("ingredient", false);
	}

	@Override
	public void create(final Request<PartOf> request, final PartOf entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	
}