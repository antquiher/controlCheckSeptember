package acme.features.chef.partOf;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.partOf.PartOf;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.entities.AbstractEntity;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefPartOfDeleteService implements AbstractDeleteService<Chef, PartOf> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefPartOfRepository repository;


	@Override
	public boolean authorise(final Request<PartOf> request) {
		assert request != null;
		
		final Integer id = request.getModel().getInteger("id");
		final Optional<AbstractEntity> result = this.repository.findById(id);
		final Principal principal = request.getPrincipal();
		
		return result.isPresent() && ((PartOf)result.get()).getRecipe().getChef().getId() == principal.getActiveRoleId()
			&& !((PartOf)result.get()).getRecipe().isPublished();
	}

	@Override
	public void validate(final Request<PartOf> request, final PartOf entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void bind(final Request<PartOf> request, final PartOf entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "quantity", "unit", "artifact.name", "artifact.code","artifact.description","artifact.retailPrice",
			"artifact.link","artifact.type");
		
	}

	@Override
	public void unbind(final Request<PartOf> request, final PartOf entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "quantity", "unit", "artifact.name", "artifact.code","artifact.description","artifact.retailPrice",
			"artifact.link","artifact.type");
		model.setAttribute("published", entity.getRecipe().isPublished());
	}

	@Override
	public PartOf findOne(final Request<PartOf> request) {
		assert request != null;
		
		PartOf result=null;
		int id;

		id = request.getModel().getInteger("id");
		final Optional<AbstractEntity> optResult = this.repository.findById(id);
		if (optResult.isPresent()) {
			result = (PartOf) optResult.get();
		}
		
		return result;
	}

	@Override
	public void delete(Request<PartOf> request, PartOf entity) {
		assert request != null;
		assert entity != null;
		
		
		this.repository.delete(entity);
		
	}

	

}