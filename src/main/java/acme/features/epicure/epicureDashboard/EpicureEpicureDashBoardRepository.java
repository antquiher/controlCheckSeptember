package acme.features.epicure.epicureDashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.fineDish.StatusType;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EpicureEpicureDashBoardRepository extends AbstractRepository {
	
	@Query("select count(f) from FineDish f where f.status = :status and f.epicure.id=:epicureId")
	Integer countFineDishByStatus(StatusType status, Integer epicureId);
	
	@Query("select avg(f.budget.amount) from FineDish f where f.status = :status and f.epicure.id=:epicureId")
	Double calcAverageFineDishBudgetByStatus(StatusType status, Integer epicureId);
	
	@Query("select stddev(f.budget.amount) from FineDish f where f.status = :status and f.epicure.id=:epicureId")
	Double calcDeviationFineDishBudgetByStatus(StatusType status, Integer epicureId);
	
	@Query("select max(f.budget.amount) from FineDish f where f.status = :status and f.epicure.id=:epicureId")
	Double calcMaximumFineDishBudgetByStatus(StatusType status, Integer epicureId);
	
	@Query("select min(f.budget.amount) from FineDish f where f.status = :status and f.epicure.id=:epicureId")
	Double calcMinimumFineDishBudgetByStatus(StatusType status, Integer epicureId);
	
	
	
	

}
