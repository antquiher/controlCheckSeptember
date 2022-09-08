package acme.features.chef.fineDish;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.entities.systemSetting.SystemSettings;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class FineDishShowService implements AbstractShowService<Chef, FineDish>{
	
	// Internal state ---------------------------------------------------------

			@Autowired
			protected FineDishRepository repository;

			// AbstractShowService<Anonymous, Announcement> interface --------------------------

			@Override
			public boolean authorise(final Request<FineDish> request) {
				assert request != null;

				Integer id = request.getModel().getInteger("id");
				Optional<AbstractEntity> result = this.repository.findById(id);
				Principal principal = request.getPrincipal();
				
				return result.isPresent() && ((FineDish)result.get()).getChef().getId() == principal.getActiveRoleId();
			}

			@Override
			public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
				assert request != null;
				assert entity != null;
				assert model != null;

				final MoneyExchange change = this.change(entity.getBudget());
				model.setAttribute("change", change.getTarget());
				
				request.unbind(entity, model, "status", "code", "request", "budget", "initialDate", "finishDate", "url", "isPublish", "epicure", "chef");
				model.setAttribute("epicureId", entity.getEpicure().getUserAccount().getId());
				model.setAttribute("isNew", false);
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
			
			protected MoneyExchange change(final Money money) {
				final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
				MoneyExchange change = new MoneyExchange();
				final SystemSettings configuration = this.repository.findConfiguration();
				
				if(!money.getCurrency().equals(configuration.getDefaultCurrency())) { //Si no es moneda por defecto
					change = this.repository.findMoneyExchageByCurrencyAndAmount(money.getCurrency(),money.getAmount());//comprobar si esta en la cache
					if(change == null) {//Precio != 0
						change = moneyExchange.computeMoneyExchange(money, configuration.getDefaultCurrency());
						this.repository.save(change);
					}
				}else {//Si es moneda por defecto
					change.setSource(money);
					change.setTarget(money);
					change.setCurrencyTarget(configuration.getDefaultCurrency());
					change.setDate(new Date(System.currentTimeMillis()));		
				}
				return change;
			}

}
