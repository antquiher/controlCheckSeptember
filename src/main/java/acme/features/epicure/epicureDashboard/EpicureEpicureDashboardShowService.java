package acme.features.epicure.epicureDashboard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.epicureDashboard.EpicureDashboard;
import acme.entities.fineDish.StatusType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;

@Service
public class EpicureEpicureDashboardShowService implements AbstractShowService<Epicure, EpicureDashboard>{
	
	@Autowired
	protected EpicureEpicureDashBoardRepository repository;
	
	@Override
	public boolean authorise(final Request<EpicureDashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind( final Request<EpicureDashboard> request,  final EpicureDashboard entity,  final Model model) {
		assert request != null;	
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalFineDish", "averageFineDishBudget", "deviationFineDishBudget", "maximumFineDishBudget", "minimumFineDishBudget");
		model.setAttribute("fineDishStatuses", StatusType.values());

	}

	@Override
	public EpicureDashboard findOne( final Request<EpicureDashboard> request) {
		assert request != null;
		
		final EpicureDashboard result = new EpicureDashboard();
		final Principal principal = request.getPrincipal();
		final Integer epicureId = principal.getActiveRoleId();
		
		final Map<StatusType, Integer> totalFineDish = new EnumMap<>(StatusType.class);
		for(final StatusType status: StatusType.values()) {
			final Integer total = this.repository.countFineDishByStatus(status, epicureId);
			totalFineDish.put(status, total != null ? total : 0);
		}
		
		 final Map<StatusType, Double> averageFineDishBudget = new EnumMap<>(StatusType.class);
		for( final StatusType status: StatusType.values()) {
			 final Double averageBudget = this.repository.calcAverageFineDishBudgetByStatus(status, epicureId);
			 final Double averageBudgetFormat = this.formatDouble(averageBudget);
			averageFineDishBudget.put(status, averageBudgetFormat != null ? averageBudgetFormat : 0);
		}
		
		 final Map<StatusType, Double> deviationFineDishBudget = new EnumMap<>(StatusType.class);
		for( final StatusType status: StatusType.values()) {
			 final Double deviationBudget = this.repository.calcDeviationFineDishBudgetByStatus(status, epicureId);
			 final Double deviationBudgetFormat = this.formatDouble(deviationBudget);
			deviationFineDishBudget.put(status, deviationBudgetFormat != null ? deviationBudgetFormat : 0);
		}
		
		 final Map<StatusType, Double> maximumFineDishBudget = new EnumMap<>(StatusType.class);
		for( final StatusType status: StatusType.values()) {
			 final Double maximumBudget = this.repository.calcMaximumFineDishBudgetByStatus(status, epicureId);
			 final Double maximumBudgetFormat = this.formatDouble(maximumBudget);
			maximumFineDishBudget.put(status, maximumBudgetFormat != null ? maximumBudgetFormat : 0);
		}
		
		 final Map<StatusType, Double> minimumFineDishBudget = new EnumMap<>(StatusType.class);
		for( final StatusType status: StatusType.values()) {
			 final Double minimumBudget = this.repository.calcMinimumFineDishBudgetByStatus(status, epicureId);
			 final Double minimumBudgetFormat = this.formatDouble(minimumBudget);
			minimumFineDishBudget.put(status, minimumBudgetFormat != null ? minimumBudgetFormat : 0);
		}
		
		result.setTotalFineDish(totalFineDish);
		result.setAverageFineDishBudget(averageFineDishBudget);
		result.setDeviationFineDishBudget(deviationFineDishBudget);
		result.setMaximumFineDishBudget(maximumFineDishBudget);
		result.setMinimumFineDishBudget(minimumFineDishBudget);
		
		return result; 
	}
	
	protected Double formatDouble( final Double number) {
		return number != null ? BigDecimal.valueOf(number).setScale(2, RoundingMode.HALF_UP).doubleValue() : 0;
	}
	
	

}
