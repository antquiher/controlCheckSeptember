package acme.features.chef.recipes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipe.Recipe;
import acme.entities.systemSetting.SystemSettings;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;
import acme.spamFilter.SpamFilter;

@Service
public class ChefRecipeCreateService implements AbstractCreateService<Chef, Recipe> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefRecipeRepository repository;

	// AbstractCreateService<Chef, Recipe> interface --------------


	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;

		return true;
	}

	@Override
	public Recipe instantiate(final Request<Recipe> request) {
		assert request != null;

		Recipe result;
		Chef chf;

		final int i =request.getPrincipal().getActiveRoleId();
		chf = this.repository.findOneInventorByInventorId(i);
		result = new Recipe();
		
		result.setPublished(false);
		result.setChef(chf);
		
		return result;
	}

	@Override
	public void bind(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "heading", "description", "preparationNotes", "link");
		
	}

	@Override
	public void validate(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		SystemSettings s = this.repository.findAllSpanTuples();
		SpamFilter spamFilter = new SpamFilter(s.getSpamTuplesFormatted(), s.getSpamThreshold());
		//---Heading
		if (!errors.hasErrors("heading")) {
			errors.state(request, entity.getHeading().length()<101, "heading", "chef.recipe.error.heading.a-lot-of-words");
			errors.state(request, spamFilter.checkIsNotSpam(entity.getHeading()), "heading", "chef.recipe.error.heading.spam-threshold");
		}
		//---Code
		if(!errors.hasErrors("code")) {
			final Optional<Recipe> existingCode = this.repository.findRecipeByCode(entity.getCode());
			if(existingCode.isPresent())
				errors.state(request, existingCode.get().getId() == entity.getId(), "code", "chef.recipe.error.code.repeated");
		}
		//---description
		if(!errors.hasErrors("description")) {
			errors.state(request, entity.getDescription().length()<256, "description", "chef.recipe.error.description.a-lot-of-words");
			errors.state(request, spamFilter.checkIsNotSpam(entity.getDescription()), "description", "chef.recipe.error.description.spam-threshold");
		}
		//---preparationNotes
		if(!errors.hasErrors("preparationNotes")) {
			errors.state(request, entity.getPreparationNotes().length()<256, "preparationNotes", "chef.recipe.error.preparationNotes.a-lot-of-words");
			errors.state(request, spamFilter.checkIsNotSpam(entity.getPreparationNotes()), "preparationNotes", "chef.recipe.error.preparationNotes.spam-threshold");
		}
		
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "heading", "description", "preparationNotes", "link");
		model.setAttribute("isNew", true);
	}

	@Override
	public void create(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}