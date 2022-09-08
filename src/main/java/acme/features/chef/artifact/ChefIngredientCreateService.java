package acme.features.chef.artifact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artifact.Artifact;
import acme.entities.artifact.ArtifactType;
import acme.entities.systemSetting.SystemSettings;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;
import acme.spamFilter.SpamFilter;

@Service
public class ChefIngredientCreateService implements AbstractCreateService<Chef, Artifact> {

	// Internal state ---------------------------------------------------------

	@Autowired
		protected ChefArtifactRepository repository;

	// AbstractCreateService<Inventor, Artifact> interface --------------
	
	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;

		return true;
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
		model.setAttribute("isNew", true);
		model.setAttribute("isIngredient", true);
	}

	@Override
	public Artifact instantiate(final Request<Artifact> request) {
		assert request != null;

		Artifact result;

		result = new Artifact();
		final int id = request.getPrincipal().getActiveRoleId();
		final Chef chef = this.repository.findOneChefByChefId(id);
		result.setChef(chef);
		result.setType(ArtifactType.INGREDIENT);
		result.setPublished(false);

		return result;
	}

	@Override
	public void validate(final Request<Artifact> request, final Artifact entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final SystemSettings s = this.repository.findSystemSetting();
		
		final Artifact artifact = this.repository.findByCode(entity.getCode());
		if(entity.getRetailPrice()!=null) {
		errors.state(request, s.getAcceptedCurrencies().contains(entity.getRetailPrice().getCurrency()) ,
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
	public void create(final Request<Artifact> request, final Artifact entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	public Double checkSpam(final String[] text) {
		//--Initial data
		final SystemSettings ss = this.repository.findAllSpanTuples();
		final String[] strongWords = ss.getSpamTuples().toLowerCase().replace("\\(", "").replace(", ", ",").split("\\),");;
		Integer nWord = 0;
		Double spamValue= 0.;
		nWord = text.length;
		//--Method
		for(int i = 0; i<strongWords.length;i++) {
			final String[] spamTuple = strongWords[i].toLowerCase().split(",");
			final String[] spamTerm = spamTuple[0].split(" ");
			for(int o = 0; o<nWord; o++) {
				boolean b = false;
				final int aux = nWord-spamTerm.length+1;
				if(aux>0) {
					for(int p = 0; p<spamTerm.length; p++) {
						b = true;
						
						if(!spamTerm[p].equals(text[o+p])) {
							b=false;
							break;
						}
					}
					if(b) {
						final Double spamTermWeight = Double.parseDouble(spamTuple[1]);
						spamValue += spamTermWeight/aux;
					}
				}
			}
		}
		return spamValue;
	}
}
