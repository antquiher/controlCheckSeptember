package acme.features.chef.artifact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artifact.Artifact;
import acme.entities.artifact.ArtifactType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefUtensilListService implements AbstractListService<Chef, Artifact> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefArtifactRepository repository;

	// AbstractListService<Chef, Artifact> interface -------------------------

	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Artifact> findMany(final Request<Artifact> request) {
		assert request != null;

		Collection<Artifact> result;

		Principal principal = request.getPrincipal();
		result = this.repository.findManyByArtifactTypeAndChef(ArtifactType.UTENSIL, principal.getActiveRoleId());

		return result;
	}

	@Override
	public void unbind(final Request<Artifact> request, final Artifact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "description", "isPublished", "retailPrice", "link");
	}
	
	@Override
	public void unbind(final Request<Artifact> request, final Collection<Artifact> entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("isIngredient", false);		
	}
}
