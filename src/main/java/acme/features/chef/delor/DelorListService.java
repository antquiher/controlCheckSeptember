package acme.features.chef.delor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.delor.Delor;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class DelorListService implements AbstractListService<Chef, Delor>{
	
	// Internal state ---------------------------------------------------------

			@Autowired
			protected DelorRepository repository;

			// AbstractListService<Anonymous, Announcement>  interface -------------------------


			@Override
			public boolean authorise(final Request<Delor> request) {
				assert request != null;

				return true;
			}
			
			@Override
			public Collection<Delor> findMany(final Request<Delor> request) {
				assert request != null;

				Collection<Delor> result;
				result = this.repository.findManyPimpam();

				return result;
			}
			
			@Override
			public void unbind(final Request<Delor> request, final Delor entity, final Model model) {
				assert request != null;
				assert entity != null;
				assert model != null;

				request.unbind(entity, model, "code", "instantiationMoment", "title","description");
			}

}
