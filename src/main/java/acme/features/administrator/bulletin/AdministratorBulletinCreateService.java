package acme.features.administrator.bulletin;
import static org.mockito.ArgumentMatchers.endsWith;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletin.Bulletin;
import acme.entities.systemSetting.SystemSettings;
import acme.features.authenticated.bulletin.AuthenticatedBulletinRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;
import acme.spamFilter.SpamFilter;

@Service
public class AdministratorBulletinCreateService implements AbstractCreateService<Administrator, Bulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedBulletinRepository repository;
	

	// AbstractCreateService<Administrator, Chirp> interface --------------


	@Override
	public boolean authorise(final Request<Bulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public Bulletin instantiate(final Request<Bulletin> request) {
		assert request != null;


		return new Bulletin();
	}

	@Override
	public void bind(final Request<Bulletin> request, final Bulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final Date moment= new Date(System.currentTimeMillis() - 1);
    
		request.bind(entity, errors, "heading", "text", "link");
		entity.setInstantiationMoment(moment);
		entity.setFlag(true);
	}

	@Override
	public void validate(final Request<Bulletin> request, final Bulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean confirmation;

		SystemSettings s = this.repository.findAllSpanTuples();
		SpamFilter spamFilter = new SpamFilter(s.getSpamTuplesFormatted(), s.getSpamThreshold());
		
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "administrator.bulletin.form.label.confirmation.message");
		
		if(!errors.hasErrors("heading")) {
			errors.state(request, spamFilter.checkIsNotSpam(entity.getHeading()), "heading", "chef.bulletin.error.heading.spam-threshold");
		}
		if(!errors.hasErrors("text")) {
			errors.state(request, spamFilter.checkIsNotSpam(entity.getText()), "text", "chef.bulletin.error.text.spam-threshold");
		}
	}

	@Override
	public void unbind(final Request<Bulletin> request, final Bulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "heading", "text", "link");
		model.setAttribute("confirmation", false);
	}

	@Override
	public void create(final Request<Bulletin> request, final Bulletin entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}