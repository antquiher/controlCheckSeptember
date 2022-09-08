package acme.features.any.peep;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.peep.Peep;
import acme.entities.systemSetting.SystemSettings;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;
import acme.spamFilter.SpamFilter;

@Service
public class PeepCreateService implements AbstractCreateService<Any, Peep>{
	
	@Autowired
	protected PeepRepository repository;

	// AbstractCreateService<Administrator, Shout> interface --------------


	@Override
	public boolean authorise(final Request<Peep> request) {
		assert request != null;

		return true;
	}

	@Override
	public Peep instantiate(final Request<Peep> request) {
		assert request != null;

		Peep result;

		result = new Peep();

		return result;
	}

	@Override
	public void bind(final Request<Peep> request, final Peep entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Date instantiationMoment;

		instantiationMoment = new Date(System.currentTimeMillis() - 1);
	
		request.bind(entity, errors, "heading", "writer", "text", "email");
		entity.setInstantiationMoment(instantiationMoment);
		
	}

	@Override
	public void validate(final Request<Peep> request, final Peep entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		SystemSettings s = this.repository.findAllSpanTuples();
		SpamFilter spamFilter = new SpamFilter(s.getSpamTuplesFormatted(), s.getSpamThreshold());
		
		final Boolean isConfirmed = request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "any.peep.form.error.must-confirm");
		
		if(!errors.hasErrors("heading")) {
			errors.state(request, spamFilter.checkIsNotSpam(entity.getHeading()), "heading", "any.peep.error.heading.spam-threshold");
		}

		if(!errors.hasErrors("writer")) {
			errors.state(request, spamFilter.checkIsNotSpam(entity.getWriter()), "writer", "any.peep.error.writer.spam-threshold");
		}
		
		if(!errors.hasErrors("text")) {
			errors.state(request,spamFilter.checkIsNotSpam(entity.getText()), "text", "any.peep.error.text.spam-threshold");
		}
	}

	@Override
	public void unbind(final Request<Peep> request, final Peep entity, final Model model) {
		assert request != null; 
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "heading", "writer", "text", "email");
		model.setAttribute("confirm", "false");
		model.setAttribute("isNew", true);
	}

	@Override
	public void create(final Request<Peep> request, final Peep entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
