package acme.features.epicure.fineDish;



import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.entities.fineDish.StatusType;
import acme.entities.systemSetting.SystemSettings;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;
import acme.roles.Epicure;

@Service
public class FineDishCreateService implements AbstractCreateService<Epicure, FineDish>{
	
	@Autowired
	protected FineDishEpicureRepository repository;
		
	// AbstractCreateService<Patron, Patronage> interface ---------------------
	
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

		
		
		request.bind(entity, errors, "code", "request", "budget", "initialDate",
			"finishDate", "url");
		
		Model model;
		Chef selectedChef;

		model = request.getModel();
		selectedChef = this.repository.findChefById(Integer.parseInt(model.getString("chefs")));

		entity.setChef(selectedChef);

	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		List<Chef> chefs;
		
		chefs=this.repository.findAllChefs();	
	
		request.unbind(entity, model, "code", "request", "budget", "initialDate",
			"finishDate", "url");
		
		model.setAttribute("isNew", true);
		model.setAttribute("chefs", chefs);
	}

	@Override
	public FineDish instantiate(final Request<FineDish> request) {
		assert request != null;
		
		FineDish result;
		
		Epicure epicure = this.repository.findEpicureById(request.getPrincipal().getActiveRoleId());
		
		result = new FineDish();
		
		
		result.setEpicure(epicure);
		result.setStatus(StatusType.PROPOSED);
		result.setPublish(false);
		
		return result;
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
		if(entity.getInitialDate()!=null) {
		initialDate.setTime(entity.getInitialDate());
		}
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
	public void create(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}
	

}
