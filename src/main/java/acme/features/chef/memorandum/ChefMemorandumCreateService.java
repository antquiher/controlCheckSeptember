/*
 * AdministratorAnnouncementCreateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.chef.memorandum;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.entities.memorandum.Memorandum;
import acme.features.chef.fineDish.FineDishRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefMemorandumCreateService implements AbstractCreateService<Chef, Memorandum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefMemorandumRepository repository;
	
	@Autowired
	protected FineDishRepository fineDishRepository;

	// AbstractCreateService<Administrator, Announcement> interface --------------


	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Memorandum> request, final Memorandum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "report", "link");
		
		final String code1 =(String) request.getModel().getAttribute("finedishes");
		System.out.println("juan" + code1);
		final FineDish fd = this.fineDishRepository.findOneFineDishByCode(code1);
		System.out.println("pepe" + fd);
		entity.setFineDish(fd);
		
		String code ="";
		final String fineDishCode = entity.getFineDish().getCode();
		final int id = this.repository.findAllMemoranda().size()+1;
		if(id<10) {
			code= code+fineDishCode+":000"+id;
		}else if(id<100){
			code= code+fineDishCode+":00"+id;
		}else if(id<1000){
			code= code+fineDishCode+":0"+id;
		}else{
			code= code+fineDishCode+id;
		}
		
		entity.setCode(code);
		
		System.out.println("aa" + entity.getCode());
	}

	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "report", "link");
		
		model.setAttribute("isNew", true);
		model.setAttribute("confirmation", false);
		model.setAttribute("finedishes", this.fineDishRepository.findManyFineDish());
	}

	@Override
	public Memorandum instantiate(final Request<Memorandum> request) {
		assert request != null;

		Memorandum result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Memorandum();
		result.setInstantiationDate(moment);

		return result;
	}

	@Override
	public void validate(final Request<Memorandum> request, final Memorandum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
	}

	@Override
	public void create(final Request<Memorandum> request, final Memorandum entity) {
		assert request != null;
		assert entity != null;

		
		
		this.repository.save(entity);
	}

}
