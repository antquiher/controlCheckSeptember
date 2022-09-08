package acme.features.any.peep;


import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.peep.Peep;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class PeepListService implements AbstractListService<Any, Peep>{
	
	// Internal state ---------------------------------------------------------

			@Autowired
			protected PeepRepository repository;

			// AbstractListService<Anonymous, Announcement>  interface -------------------------


			@Override
			public boolean authorise(final Request<Peep> request) {
				assert request != null;

				return true;
			}
			
			@Override
			public Collection<Peep> findMany(final Request<Peep> request) {
				assert request != null;

				Collection<Peep> result;
				Calendar calendar;
				Date deadline;

				calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, -1);
				deadline = calendar.getTime();
				result = this.repository.findManyPeepOneMonth(deadline);

				return result;
			}
			
			@Override
			public void unbind(final Request<Peep> request, final Peep entity, final Model model) {
				assert request != null;
				assert entity != null;
				assert model != null;

				request.unbind(entity, model, "instantiationMoment", "heading", "writer", "text", "email");
			}

}
