package acme.features.any.userAccount;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyUserAccountRepository extends AbstractRepository{

	@Query("select ua from UserAccount ua where ua.enabled=true and " +
		"(select count(admin) from Administrator admin where admin.userAccount=ua) = 0 and " +
		"(select count(anonymous) from Anonymous anonymous where anonymous.userAccount=ua) = 0")
	Collection<UserAccount> findAllEnabled();

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);
}
