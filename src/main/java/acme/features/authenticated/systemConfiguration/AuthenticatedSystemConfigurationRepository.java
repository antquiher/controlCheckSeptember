package acme.features.authenticated.systemConfiguration;

import acme.framework.repositories.AbstractRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.systemSetting.SystemSettings;

@Repository
public interface AuthenticatedSystemConfigurationRepository extends AbstractRepository {
	
	@Query("SELECT systemSetting FROM SystemSettings systemSetting")
	Optional<SystemSettings> findOne();

}
