package acme.features.any.peep;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import acme.entities.peep.Peep;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class PeepShowService implements AbstractShowService<Any, Peep>{
	
	@Autowired
	protected PeepRepository repository;

	// AbstractShowService<Anonymous, Announcement> interface --------------------------

	@Override
	public boolean authorise(final Request<Peep> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void unbind(final Request<Peep> request, final Peep entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "instantiationMoment", "heading", "writer", "text", "email");
	
	}

	@Override
	public Peep findOne(final Request<Peep> request) {
		assert request != null;

		Peep result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOnePeepById(id);

		return result;
	}

}
