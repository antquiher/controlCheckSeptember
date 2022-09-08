package acme.features.authenticated.bulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletin.Bulletin;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedBulletinShowService implements AbstractShowService<Authenticated, Bulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedBulletinRepository repository;

	// AbstractShowService<Anonymous, Bulletin> interface --------------------------

	@Override
	public boolean authorise(final Request<Bulletin> request) {
		assert request != null;

		int id;
		Bulletin bulletin;

		id = request.getModel().getInteger("id");
		bulletin = this.repository.findOneBulletinById(id);

		return bulletin != null;
	}

	@Override
	public void unbind(final Request<Bulletin> request, final Bulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "instantiationMoment", "heading", "text", "link");
	}

	@Override
	public Bulletin findOne(final Request<Bulletin> request) {
		assert request != null;

		Bulletin result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneBulletinById(id);

		return result;
	}

}
