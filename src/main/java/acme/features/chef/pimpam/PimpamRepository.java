package acme.features.chef.pimpam;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artifact.Artifact;
import acme.entities.pimpam.Pimpam;
import acme.entities.systemSetting.SystemSettings;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;
import acme.roles.Epicure;

@Repository
public interface PimpamRepository extends AbstractRepository{
	
	@Query("select a from Pimpam a where a.id = :id")
	Pimpam findOnePimpamById(int id);
	
	@Query("select artifact from Artifact artifact")
	List<Artifact> findAllArtifact();
	
	@Query("select pimpam from Pimpam pimpam")
	List<Pimpam> findAllPimpam();
	
	@Query("select artifact from Artifact artifact where artifact.id = :id")
	Artifact findArtifactById(int id);
	
	@Query("select a from Artifact a LEFT JOIN Pimpam c ON c.artifact=a WHERE c IS NULL")
	List<Artifact> findArtifactList();
	
	@Query("select a from Pimpam a where a.code = :code")
	Pimpam findOnePimpamByCode(String code);

	@Query("select a from Pimpam a")
	Collection<Pimpam> findManyPimpam();
	
	@Query("select a from Pimpam a where a.artifact.id = :i")
	Collection<Pimpam> findManyPimpamByArtifact(Integer i);

	@Query("select s from SystemSettings s")
	SystemSettings findConfiguration();

}
