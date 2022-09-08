package acme.features.chef.fineDish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import acme.entities.fineDish.FineDish;
import acme.entities.fineDish.StatusType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;


@Service
public class FineDishChefUpdateService implements AbstractUpdateService<Chef, FineDish>{

	
	@Autowired
	protected FineDishRepository repository;

	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "id", "status", "code", "request", "budget", "initialDate", "finishDate", "url");
		
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;

		FineDish result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneFineDishById(id);

		return result;
	}

	@Override
	public void bind(Request<FineDish> request, FineDish entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "id", "status", "code", "request", "budget", "initialDate", "finishDate", "url");
		
	}

	@Override
	public void validate(Request<FineDish> request, FineDish entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void update(Request<FineDish> request, FineDish entity) {
		assert request !=null;
		assert entity !=null;
		
		StatusType status;
		
		status = StatusType.valueOf(request.getModel().getString("status"));
		
		entity.setStatus(status);
		this.repository.save(entity);
		
	}
	
	@Override
	public void onSuccess(final Request<FineDish> request, final Response<FineDish> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}
	
}
