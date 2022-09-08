package acme.features.epicure.memorandum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.memorandum.Memorandum;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EpicureMemorandumRepository extends AbstractRepository {

	@Query("select m from Memorandum m where m.id = :id")
	Memorandum findOneMemorandumById(int id);
	
	@Query("select m from Memorandum m")
	Collection<Memorandum> findAllMemoranda();

	@Query("select m from Memorandum m where m.fineDish.epicure.id = :epicure")
	Collection<Memorandum> findManyMemoranda(int epicure);

}
