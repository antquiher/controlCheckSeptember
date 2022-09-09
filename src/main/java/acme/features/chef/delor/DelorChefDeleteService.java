package acme.features.chef.delor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.delor.Delor;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class DelorChefDeleteService implements AbstractDeleteService<Chef, Delor>{
	
	@Autowired
	protected DelorRepository repository;
	
	// AbstractUpdateService<Patron, Patronage> interface ---------------------
	
	@Override
	public boolean authorise(final Request<Delor> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Delor> request, final Delor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors,"code", "instantiationMoment", "title", "description" ,"startPeriod",
			"finishPeriod", "budget", "link");
	}

	@Override
	public void unbind(final Request<Delor> request, final Delor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"code", "instantiationMoment", "title", "description" ,"startPeriod",
			"finishPeriod", "budget", "link");
		
		model.setAttribute("artifactId", entity.getArtifact().getId());
		model.setAttribute("artifactPublish", entity.getArtifact().isPublished());
		
		
	}

	@Override
	public Delor findOne(final Request<Delor> request) {
		assert request != null;
		
		Delor finedish;
		int id;
		
		id = request.getModel().getInteger("id");
		finedish = this.repository.findOnePimpamById(id);
		
		return finedish;
	}

	@Override
	public void validate(final Request<Delor> request, final Delor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<Delor> request, final Delor entity) {
		assert request != null;
		assert entity != null;
		
		
		
	
		this.repository.delete(entity);
	}

}
