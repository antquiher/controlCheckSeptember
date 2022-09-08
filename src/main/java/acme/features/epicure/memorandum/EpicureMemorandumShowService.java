package acme.features.epicure.memorandum;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.memorandum.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.AbstractEntity;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;

@Service
public class EpicureMemorandumShowService implements AbstractShowService<Epicure, Memorandum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicureMemorandumRepository repository;

	// AbstractShowService<Anonymous, Memorandum> interface --------------------------

	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;

		Integer id = request.getModel().getInteger("id");
		Optional<AbstractEntity> result = this.repository.findById(id);
		Principal principal = request.getPrincipal();
		
		return result.isPresent() && ((Memorandum)result.get()).getFineDish().getEpicure().getId() == principal.getActiveRoleId();
	}

	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "report");
	}

	@Override
	public Memorandum findOne(final Request<Memorandum> request) {
		assert request != null;

		Memorandum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneMemorandumById(id);

		return result;
	}
}
