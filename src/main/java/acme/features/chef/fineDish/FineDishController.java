package acme.features.chef.fineDish;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.fineDish.FineDish;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;


@Controller
public class FineDishController extends AbstractController<Chef, FineDish> {
	
	// Internal state ---------------------------------------------------------

			@Autowired
			protected FineDishListService	listService;

			@Autowired
			protected FineDishShowService	showService;
			
			@Autowired
			protected FineDishChefUpdateService	updateService;
			

			// Constructors -----------------------------------------------------------


			@PostConstruct
			protected void initialise() {
				super.addCommand("list", this.listService);
				super.addCommand("show", this.showService);
				super.addCommand("update-status", "update", this.updateService);
			}

}
