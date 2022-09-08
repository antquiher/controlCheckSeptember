package acme.features.epicure.fineDish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Epicure;

@Service
public class FineDishDeleteService implements AbstractDeleteService<Epicure, FineDish>{
	
	@Autowired
	protected FineDishEpicureRepository repository;
	
	// AbstractUpdateService<Patron, Patronage> interface ---------------------
	
	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "status", "code", "request", "budget",
			"initialDate", "finishDate", "url");
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "code", "request", "budget",
			"initialDate", "finishDate", "url", "isPublish", "chef.organisation", "chef.assertion",
			"chef.url");
		
		
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;
		
		FineDish finedish;
		int id;
		
		id = request.getModel().getInteger("id");
		finedish = this.repository.findOneFineDishById(id);
		
		return finedish;
	}

	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;
		
		
		
	
		this.repository.delete(entity);
	}

}
