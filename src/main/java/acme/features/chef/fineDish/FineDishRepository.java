package acme.features.chef.fineDish;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.fineDish.FineDish;
import acme.entities.systemSetting.SystemSettings;
import acme.forms.MoneyExchange;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface FineDishRepository extends AbstractRepository{
	
	@Query("select a from FineDish a where a.id = :id")
	FineDish findOneFineDishById(int id);
	
	@Query("select a from FineDish a where a.code = :code")
	FineDish findOneFineDishByCode(String code);

	@Query("select a from FineDish a")
	Collection<FineDish> findManyFineDish();
	
	@Query("select a from FineDish a where a.chef.id = :i")
	Collection<FineDish> findManyFineDishByChef(Integer i);

	@Query("select s from SystemSettings s")
	SystemSettings findConfiguration();
	
	@Query("select me from MoneyExchange me where me.source.currency = :currency and me.source.amount = :amount")
    MoneyExchange findMoneyExchageByCurrencyAndAmount(String currency, Double amount);
	
}
