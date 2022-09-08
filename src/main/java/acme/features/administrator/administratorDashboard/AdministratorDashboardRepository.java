package acme.features.administrator.administratorDashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artifact.Artifact;
import acme.entities.artifact.ArtifactType;
import acme.entities.fineDish.StatusType;
import acme.entities.pimpam.Pimpam;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(a) from Artifact a where a.type = :type")
	Integer countArtifactByType(ArtifactType type);

	@Query("select s.acceptedCurrencies from SystemSettings s")
	String findAcceptedCurrencies();

	@Query("select avg(a.retailPrice.amount) from Artifact a where a.type = :type and a.retailPrice.currency = :currency")
	Double calcAverageArtifactRetailPriceByTypeAndCurrency(ArtifactType type, String currency);
	
	@Query("select stddev(a.retailPrice.amount) from Artifact a where a.type = :type and a.retailPrice.currency = :currency")
	Double calcDeviationArtifactRetailPriceByTypeAndCurrency(ArtifactType type, String currency);

	@Query("select min(a.retailPrice.amount) from Artifact a where a.type = :type and a.retailPrice.currency = :currency")
	Double calcMinimumArtifactRetailPriceByTypeAndCurrency(ArtifactType type, String currency);
	
	@Query("select max(a.retailPrice.amount) from Artifact a where a.type = :type and a.retailPrice.currency = :currency")
	Double calcMaximumArtifactRetailPriceByTypeAndCurrency(ArtifactType type, String currency);

	@Query("select count(f) from FineDish f where f.status = :status")
	Integer countFineDishByStatus(StatusType status);

	@Query("select avg(f.budget.amount) from FineDish f where f.status = :status")
	Double calcAverageFineDishBudgetByStatus(StatusType status);

	@Query("select stddev(f.budget.amount) from FineDish f where f.status = :status")
	Double calcDeviationFineDishBudgetByStatus(StatusType status);

	@Query("select max(f.budget.amount) from FineDish f where f.status = :status")
	Double calcMaximumFineDishBudgetByStatus(StatusType status);

	@Query("select min(f.budget.amount) from FineDish f where f.status = :status")
	Double calcMinimumFineDishBudgetByStatus(StatusType status);
	
	//PIMPAM
	
	@Query("select avg(f.budget.amount) from Pimpam f where f.budget.currency = :currency")
	Double calcAveragePimpamBudgetByCurrency(String currency);

	@Query("select stddev(f.budget.amount) from Pimpam f where f.budget.currency = :currency")
	Double calcDeviationPimpamBudgetByCurrency(String currency);

	@Query("select max(f.budget.amount) from Pimpam f where f.budget.currency = :currency")
	Double calcMaximumPimpamBudgetByCurrency(String currency);

	@Query("select min(f.budget.amount) from Pimpam f where f.budget.currency = :currency")
	Double calcMinimumPimpamBudgetByCurrency(String currency);
	
	@Query("select c from Pimpam c")
	Collection<Pimpam> findAllPimpam();
	
	@Query("select a from Artifact a ")
	Collection<Artifact> findAllArtifact();

}
