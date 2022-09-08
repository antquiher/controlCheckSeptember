package acme.features.chef.artifact;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.forms.MoneyExchange;
import acme.entities.artifact.Artifact;
import acme.entities.artifact.ArtifactType;
import acme.entities.partOf.PartOf;
import acme.entities.systemSetting.SystemSettings;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefArtifactRepository extends AbstractRepository {

	@Query("select a from Artifact a where a.type = :artifactType and a.chef.id = :artifactId")
	Collection<Artifact> findManyByArtifactTypeAndChef(ArtifactType artifactType, Integer artifactId);

	@Query("select c from Chef c where c.id = :id")
	Chef findOneChefByChefId(int id);

	@Query("select s from SystemSettings s")
	SystemSettings findSystemSetting();

	@Query("select a from Artifact a where a.code = :code")
	Artifact findByCode(String code);

	@Query("select a from Artifact a where a.id = :id")
	Artifact findOneArtifactById(int id);

	@Query("select a from Artifact a where code = :code and id <> :id")
	Artifact findByCodeAndDifferentId(String code, int id);

	@Query("select p from PartOf p where p.artifact = :artifact")
	Collection<PartOf> findAllPartoOfByArtifact(Artifact artifact);

	@Query("select s from SystemSettings s")
	SystemSettings findAllSpanTuples();

	
	@Query("select me from MoneyExchange me where me.source.currency = :currency and me.source.amount = :amount")
    MoneyExchange findMoneyExchageByCurrencyAndAmount(String currency, Double amount);

	@Query("select s from SystemSettings s")
	SystemSettings findConfiguration();

}
