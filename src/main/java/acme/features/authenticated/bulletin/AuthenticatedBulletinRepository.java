package acme.features.authenticated.bulletin	;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletin.Bulletin;
import acme.entities.systemSetting.SystemSettings;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedBulletinRepository extends AbstractRepository {

	@Query("select b from Bulletin b where b.id = :id")
	Bulletin findOneBulletinById(int id);

	@Query("select b from Bulletin b where b.instantiationMoment > :deadline")
    Collection<Bulletin> findManyBulletinOneMonth(Date deadline);
	
	@Query("select b from Bulletin b where b.instantiationMoment = :date")
	Bulletin findBulletinToPatch(Date date);

	@Query("select s from SystemSettings s")
	SystemSettings findAllSpanTuples();
}
