package acme.features.epicure.pimpam;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artifact.Artifact;
import acme.entities.pimpam.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;
import acme.roles.Epicure;

@Service
public class PimpamEpiShowService implements AbstractShowService<Epicure, Pimpam>{
	
	// Internal state ---------------------------------------------------------

			@Autowired
			protected PimpamEpiRepository repository;

			// AbstractShowService<Anonymous, Announcement> interface --------------------------

			@Override
			public boolean authorise(final Request<Pimpam> request) {
				assert request != null;

				
				return true;
			}

			@Override
			public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
				assert request != null;
				assert entity != null;
				assert model != null;

			
				
				request.unbind(entity, model, "code", "instantiationMoment", "title", "description", "startPeriod", "finishPeriod", "budget", "link");
				model.setAttribute("isNew", false);
				
				
			}

			@Override
			public Pimpam findOne(final Request<Pimpam> request) {
				assert request != null;

				Pimpam result;
				int id;

				id = request.getModel().getInteger("id");
				result = this.repository.findOnePimpamById(id);

				return result;
			}
			
			

}
