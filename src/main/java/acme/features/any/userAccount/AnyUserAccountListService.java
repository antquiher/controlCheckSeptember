package acme.features.any.userAccount;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;
import acme.framework.roles.UserRole;
import acme.framework.services.AbstractListService;

@Service
public class AnyUserAccountListService implements AbstractListService<Any, UserAccount>{
 
	@Autowired
	protected AnyUserAccountRepository repository;
	
	
	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request!=null;
		return true;
	}

	@Override
	public Collection<UserAccount> findMany(final Request<UserAccount> request) {
		assert request!=null;
		
		Collection<UserAccount> result;
		
		
		result=this.repository.findAllEnabled();
		
		for(final UserAccount user:result) {
			user.getRoles().forEach(r->{});
		}
		
		return result;
	}

	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final StringBuilder buffer;
		final Collection<UserRole> roles;
		
		request.unbind(entity, model, "username", "roles", "identity.name", "identity.surname");
		
		roles=entity.getRoles();
		
		buffer = new StringBuilder();
		for(final UserRole rol:roles) {
			buffer.append(rol.getAuthorityName());
			buffer.append(" ");
		}
		
		model.setAttribute("roleList", buffer.toString());
	}

}
