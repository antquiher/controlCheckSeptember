package acme.features.any.partOf;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.partOf.PartOf;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyPartOfRecipeListService implements AbstractListService<Any, PartOf> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyPartOfRepository repository;

	// AbstractListService<Anonymous, PartOf> interface -------------------------

	@Override
	public boolean authorise(final Request<PartOf> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<PartOf> findMany(final Request<PartOf> request) {
		assert request != null;

		Collection<PartOf> result;
		
		final int masterId = request.getModel().getInteger("masterId");
		
		result = this.repository.findManyRecipesByPublisedAndArtifactId(masterId);

		return result;
	}

	@Override
	public void unbind(final Request<PartOf> request, final PartOf entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "quantity","unit","recipe.heading");
	}
	
	@Override
	public void unbind(Request<PartOf> request, Collection<PartOf> list, Model model) {
		model.setAttribute("cameFromArtifact", true);
	}
}
