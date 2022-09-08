package acme.features.chef.pimpam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.pimpam.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class PimpamChefDeleteService implements AbstractDeleteService<Chef, Pimpam>{
	
	@Autowired
	protected PimpamRepository repository;
	
	// AbstractUpdateService<Patron, Patronage> interface ---------------------
	
	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors,"code", "instantiationMoment", "title", "description" ,"startPeriod",
			"finishPeriod", "budget", "link");
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"code", "instantiationMoment", "title", "description" ,"startPeriod",
			"finishPeriod", "budget", "link");
		
		model.setAttribute("artifactId", entity.getArtifact().getId());
		model.setAttribute("artifactPublish", entity.getArtifact().isPublished());
		
		
	}

	@Override
	public Pimpam findOne(final Request<Pimpam> request) {
		assert request != null;
		
		Pimpam finedish;
		int id;
		
		id = request.getModel().getInteger("id");
		finedish = this.repository.findOnePimpamById(id);
		
		return finedish;
	}

	@Override
	public void validate(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<Pimpam> request, final Pimpam entity) {
		assert request != null;
		assert entity != null;
		
		
		
	
		this.repository.delete(entity);
	}

}
