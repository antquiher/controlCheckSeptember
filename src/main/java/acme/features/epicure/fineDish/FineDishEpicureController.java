package acme.features.epicure.fineDish;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.fineDish.FineDish;
import acme.framework.controllers.AbstractController;
import acme.roles.Epicure;


@Controller
public class FineDishEpicureController extends AbstractController<Epicure, FineDish> {
	
	// Internal state ---------------------------------------------------------

			@Autowired
			protected FineDishEpicureListService	listService;

			@Autowired
			protected FineDishEpicureShowService	showService;
			
			@Autowired
			protected FineDishCreateService	createService;
			
			@Autowired
			protected FineDishUpdateService	updateService;
			
			@Autowired
			protected FineDishDeleteService	deleteService;
			
			@Autowired
			protected FineDishPublishService	publishService;
			

			// Constructors -----------------------------------------------------------


			@PostConstruct
			protected void initialise() {
				super.addCommand("list", this.listService);
				super.addCommand("show", this.showService);
				super.addCommand("create", this.createService);
				super.addCommand("update", this.updateService);
				super.addCommand("delete", this.deleteService);
				super.addCommand("publish", "update", this.publishService);
			}

}
