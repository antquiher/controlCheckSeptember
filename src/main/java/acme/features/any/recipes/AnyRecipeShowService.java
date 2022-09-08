package acme.features.any.recipes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.AbstractEntity;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyRecipeShowService implements AbstractShowService<Any, Recipe> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyRecipeRepository repository;

	// AbstractShowService<Anonymous, Recipe> interface --------------------------

	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		
		final Integer id = request.getModel().getInteger("id");
		final Optional<AbstractEntity> result =  this.repository.findById(id);

		return result.isPresent();
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "heading", "code", "description", "preparationNotes", "link");
	}

	@Override
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;
		
		Recipe result=null;
		int id;

		id = request.getModel().getInteger("id");
		final Optional<AbstractEntity> optResult = this.repository.findById(id);
		if (optResult.isPresent()) {
			result = (Recipe) optResult.get();
		}

		assert result != null;
		
		return result;
	}
}
