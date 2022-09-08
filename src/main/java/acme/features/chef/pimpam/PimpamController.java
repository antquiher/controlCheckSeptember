package acme.features.chef.pimpam;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.pimpam.Pimpam;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;


@Controller
public class PimpamController extends AbstractController<Chef, Pimpam> {
	
	// Internal state ---------------------------------------------------------

			@Autowired
			protected PimpamListService	listService;

			@Autowired
			protected PimpamShowService	showService;
			
			@Autowired
			protected PimpamChefCreateService	createService;
			
			@Autowired
			protected PimpamChefUpdateService	updateService;
			
			@Autowired
			protected PimpamChefDeleteService	deleteService;
			

			
			

			// Constructors -----------------------------------------------------------


			@PostConstruct
			protected void initialise() {
				super.addCommand("list", this.listService);
				super.addCommand("show", this.showService);
				super.addCommand("create", this.createService);
				super.addCommand("update", this.updateService);
				super.addCommand("delete", this.deleteService);
			}

}
