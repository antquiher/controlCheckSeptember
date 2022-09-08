package acme.features.epicure.fineDish;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.fineDish.FineDish;
import acme.entities.systemSetting.SystemSettings;
import acme.forms.MoneyExchange;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;
import acme.roles.Epicure;

@Repository
public interface FineDishEpicureRepository extends AbstractRepository{
	
	@Query("select a from FineDish a where a.id = :id")
	FineDish findOneFineDishById(int id);

	@Query("select a from FineDish a")
	Collection<FineDish> findManyFineDish();
	
	@Query("select c from Chef c")
	List<Chef> findChefList();
	
	@Query("select c from SystemSettings c")
	SystemSettings findSystemSetting();
	
	@Query("select c from Chef c where c.id = :id")
	Chef findChefById(int id);
	
	@Query("select a from FineDish a where a.epicure.id = :i")
	Collection<FineDish> findManyFineDishByEpi(Integer i);
	
	@Query("select chef from Chef chef where chef.userAccount.username = :username")
	Chef findChefByUsername(String username);
	
	@Query("select epicure from Epicure epicure where epicure.id = :id")
	Epicure findEpicureById(int id);
	
	@Query("select chef from Chef chef")
	List<Chef> findAllChefs();

	@Query("select s from SystemSettings s")
	SystemSettings findConfiguration();
	
	@Query("select me from MoneyExchange me where me.source.currency = :currency and me.source.amount = :amount")
    MoneyExchange findMoneyExchageByCurrencyAndAmount(String currency, Double amount);

}
