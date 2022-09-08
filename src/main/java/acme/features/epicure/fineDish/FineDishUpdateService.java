package acme.features.epicure.fineDish;



import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.entities.systemSetting.SystemSettings;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.datatypes.Money;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Epicure;

@Service
public class FineDishUpdateService implements AbstractUpdateService<Epicure, FineDish>{
	
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
		

		
		request.bind(entity, errors, "code", "request", "budget",  "initialDate",
			"finishDate", "url");
	
		
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		

		
		request.unbind(entity, model, "status", "code", "request", "budget",
				"initialDate", "finishDate", "url", "isPublish", "chef" ,"chef.organisation", "chef.assertion",
				"chef.url");

		model.setAttribute("chefId", entity.getChef().getUserAccount().getId());
		model.setAttribute("esChef", false);
		
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
		
		Calendar d=Calendar.getInstance();
		d.add(Calendar.MONTH, 1);
		
		if (!errors.hasErrors("initialDate")) {


			errors.state(request, entity.getInitialDate().after(d.getTime()), "initialDate",
					"epicure.finedish.error.initialDate");
		}
		
		Calendar initialDate = Calendar.getInstance();
		initialDate.setTime(entity.getInitialDate());
		initialDate.add(Calendar.MONTH, 1);
		
		if (!errors.hasErrors("finishDate")) {
			

			errors.state(request, entity.getFinishDate().after(initialDate.getTime()), "finishDate",
					"epicure.finedish.error.finishDate");
		}
		
		Money money=entity.getBudget();
		final SystemSettings c = this.repository.findSystemSetting();
		
		if (!errors.hasErrors("budget")) {


			errors.state(request, money.getAmount()>=0., "budget",
					"epicure.finedish.error.budget");
			
			errors.state(request, c.getAcceptedCurrencies().contains(money.getCurrency()) ,
					  "budget", "epicure.finedish.budget.not-able-currency");
		}
		
	}

	@Override
	public void update(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;
		
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
