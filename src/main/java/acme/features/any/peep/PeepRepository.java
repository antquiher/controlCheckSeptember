package acme.features.any.peep;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.peep.Peep;
import acme.entities.systemSetting.SystemSettings;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PeepRepository extends AbstractRepository{
	
	@Query("select a from Peep a where a.id = :id")
	Peep findOnePeepById(int id);

	@Query("select a from Peep a")
	Collection<Peep> findManyPeep();
	
	@Query("select a from Peep a where a.instantiationMoment > :deadline")
	Collection<Peep> findManyPeepOneMonth(Date deadline);

	@Query("select s from SystemSettings s")
	SystemSettings findAllSpanTuples();

}
