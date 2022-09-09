package acme.features.chef.delor;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artifact.Artifact;
import acme.entities.artifact.ArtifactType;
import acme.entities.delor.Delor;
import acme.entities.systemSetting.SystemSettings;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface DelorRepository extends AbstractRepository{
	
	@Query("select a from Delor a where a.id = :id")
	Delor findOnePimpamById(int id);
	
	@Query("select artifact from Artifact artifact WHERE artifact.type=:arType")
	List<Artifact> findAllArtifact(ArtifactType arType);
	
	@Query("select Delor from Delor Delor")
	List<Delor> findAllPimpam();
	
	@Query("select artifact from Artifact artifact where artifact.id = :id")
	Artifact findArtifactById(int id);
	
	@Query("select a from Artifact a LEFT JOIN Delor c ON c.artifact=a WHERE c IS NULL")
	List<Artifact> findArtifactList();
	
	@Query("select a from Delor a where a.code = :code")
	Delor findOnePimpamByCode(String code);

	@Query("select a from Delor a")
	Collection<Delor> findManyPimpam();
	
	@Query("select a from Delor a where a.artifact.id = :i")
	Collection<Delor> findManyPimpamByArtifact(Integer i);

	@Query("select s from SystemSettings s")
	SystemSettings findConfiguration();

}
