package acme.features.chef.delor;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artifact.Artifact;
import acme.entities.artifact.ArtifactType;
import acme.entities.delor.Delor;
import acme.entities.systemSetting.SystemSettings;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class DelorChefCreateService implements AbstractCreateService<Chef, Delor>{
	
	@Autowired
	protected DelorRepository repository;
		
	// AbstractCreateService<Patron, Patronage> interface ---------------------
	
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

		
		
		request.bind(entity, errors, "title", "description", "startPeriod", "finishPeriod", "budget", "link", "auxiliarSeisNumeros");
		
		Model model;
		Artifact selectedArtifact;

		model = request.getModel();
		selectedArtifact = this.repository.findArtifactById(Integer.parseInt(model.getString("artifacts")));

		entity.setAuxiliarSeisNumeros(model.getString("auxiliarSeisNumeros"));
		entity.setArtifact(selectedArtifact);

	}

	@Override
	public void unbind(final Request<Delor> request, final Delor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		List<Artifact> artifacts;
		
		List<Delor> lp=this.repository.findAllPimpam();
		Set<Artifact> la= new HashSet<Artifact>();
		for(Delor p:lp) {
			la.add(p.getArtifact());
		}
		
		artifacts=this.repository.findAllArtifact(ArtifactType.INGREDIENT);	
	
		request.unbind(entity, model, "title", "description", "startPeriod", "finishPeriod", "budget", "link");
		
		model.setAttribute("isNew", true);
		model.setAttribute("artifacts", artifacts.stream().filter(x->!x.isPublished()).filter(y->!la.contains(y)).collect(Collectors.toList()));
		model.setAttribute("auxiliarSeisNumeros", entity.getAuxiliarSeisNumeros());
	}

	@Override
	public Delor instantiate(final Request<Delor> request) {
		assert request != null;
		
		Delor result;
		
		
		
		result = new Delor();
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		String formattedString = localDate.format(formatter);
		formattedString = "000000:"+formattedString;
		result.setCode(formattedString);
		result.setInstantiationMoment(Calendar.getInstance().getTime());
		
		return result;
	}

	@Override
	public void validate(final Request<Delor> request, final Delor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Calendar d=Calendar.getInstance();
		d.setTime(entity.getInstantiationMoment());
		d.add(Calendar.MONTH, 1);
		
		
		if (!errors.hasErrors("startPeriod")) {


			errors.state(request, entity.getStartPeriod().after(d.getTime()), "startPeriod",
					"chef.pimpam.error.month.startPeriod");
		}
		
		Calendar ds=Calendar.getInstance();
		if(entity.getStartPeriod()!=null ) {
		ds.setTime(entity.getStartPeriod());
		}
		ds.add(Calendar.DAY_OF_YEAR, 7);
		
		
		if (!errors.hasErrors("finishPeriod")) {


			errors.state(request, entity.getFinishPeriod().after(ds.getTime()), "finishPeriod",
					"chef.pimpam.error.week.finishPeriod");
		}
		
		if(!errors.hasErrors("auxiliarSeisNumeros")) {
			errors.state(request, entity.getAuxiliarSeisNumeros().length()==6, "auxiliarSeisNumeros",
				"chef.pimpam.error.not-valid-length-string.auxiliarSeisNumeros");
			boolean bol = true;
			try {
		        Integer.parseInt(entity.getAuxiliarSeisNumeros());
		    } catch (NumberFormatException nfe) {
		        bol = false;
		    }
		    
			errors.state(request, bol, "auxiliarSeisNumeros",
				"chef.pimpam.error.not-valid-string-formatter.auxiliarSeisNumeros");
		}
		
		

		
		Money money=entity.getBudget();
		final SystemSettings c = this.repository.findConfiguration();
		if (!errors.hasErrors("budget")) {


			errors.state(request, money.getAmount()>=0., "budget",
					"chef.pimpam.error.budget");
			
			errors.state(request, c.getAcceptedCurrencies().contains(money.getCurrency()) ,
					  "budget", "chef.pimpam.not-able-currency");
		}
		

		if (!errors.hasErrors("artifacts")) {
			errors.state(request, entity.getArtifact()!=null, "artifacts",
					"chef.pimpam.error.null.artifacts");
		}
		
		
	}

	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	@Override
	public void create(final Request<Delor> request, final Delor entity) {
		assert request != null;
		assert entity != null;
		
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		String formattedString = localDate.format(formatter);
		String aux = entity.getAuxiliarSeisNumeros();
		formattedString = aux+":"+formattedString;
		entity.setCode(formattedString);
		
		this.repository.save(entity);
	}
	

}
