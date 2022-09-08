package acme.features.chef.artifact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artifact.Artifact;
import acme.entities.systemSetting.SystemSettings;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;
import acme.spamFilter.SpamFilter;

@Service
public class ChefArtifactPublishService implements AbstractUpdateService<Chef, Artifact> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefArtifactRepository repository;


	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;

		boolean result = true;
		int masterId;
		Artifact art;
		Chef chef;

		masterId = request.getModel().getInteger("id");
		art = this.repository.findOneArtifactById(masterId);
		chef = art.getChef();
		result = request.isPrincipal(chef);
		
		return result;
	}

	@Override
	public void validate(final Request<Artifact> request, final Artifact entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final SystemSettings s = this.repository.findSystemSetting();
		
		final Artifact artifact = this.repository.findByCodeAndDifferentId(entity.getCode(), entity.getId());
		if(entity.getRetailPrice()!=null) {
		errors.state(request, s.getAcceptedCurrencies().contains(entity.getRetailPrice().getCurrency()),
			  "retailPrice", "chef.artifact.retailPrice.not-able-currency");
		errors.state(request, entity.getRetailPrice().getAmount() > 0, "retailPrice", "chef.artifact.code.repeated.retailPrice.non-negative");
		}
		errors.state(request, artifact == null, "code", "chef.artifact.code.repeated");

		SpamFilter spamFilter = new SpamFilter(s.getSpamTuplesFormatted(), s.getSpamThreshold());
		if(!errors.hasErrors("name")) {
			errors.state(request, spamFilter.checkIsNotSpam(entity.getName()), "name", "chef.artifact.error.name.spam-threshold");
		}
		if(!errors.hasErrors("description")) {
			errors.state(request, spamFilter.checkIsNotSpam(entity.getDescription()), "description", "chef.artifact.error.description.spam-threshold");
		}
	}

	@Override
	public void bind(final Request<Artifact> request, final Artifact entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "name", "code", "description", "retailPrice", "link");
		
	}

	@Override
	public void unbind(final Request<Artifact> request, final Artifact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "description", "retailPrice", "link");
	}

	@Override
	public Artifact findOne(final Request<Artifact> request) {
		assert request != null;
		
		Artifact result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneArtifactById(id);

		return result;
	}

	@Override
	public void update(final Request<Artifact> request, final Artifact entity) {
		assert request != null;
		assert entity != null;
		
		entity.setPublished(true);
		this.repository.save(entity);
	}
}