package acme.features.epicure.fineDish;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.entities.systemSetting.SystemSettings;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Epicure;


@Service
public class FineDishPublishService implements AbstractUpdateService<Epicure, FineDish>{
	
	@Autowired
	protected FineDishEpicureRepository repository;
	
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
		
		request.bind(entity, errors,  "status", "code", "request", "budget", "initialDate", "finishDate", "url");
		
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "code", "request", "budget", "initialDate", "finishDate", "url", "isPublish");
		
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
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Calendar d=Calendar.getInstance();
		d.add(Calendar.MONTH, 1);
		
		if (!errors.hasErrors("initialDate")) {


			errors.state(request, entity.getInitialDate().after(d.getTime()), "initialDate",
					"epicure.finedish.error.initialDate");
		}
		
		final Calendar initialDate = Calendar.getInstance();
		initialDate.setTime(entity.getInitialDate());
		initialDate.add(Calendar.MONTH, 1);
		
		if (!errors.hasErrors("finishDate")) {
			

			errors.state(request, entity.getFinishDate().after(initialDate.getTime()), "finishDate",
					"epicure.finedish.error.finishDate");
		}
		
		final Money money=entity.getBudget();
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
		
		entity.setPublish(true);
		this.repository.save(entity);
		
	}

}
