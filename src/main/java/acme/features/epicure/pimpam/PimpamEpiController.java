package acme.features.epicure.pimpam;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.pimpam.Pimpam;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;
import acme.roles.Epicure;


@Controller
public class PimpamEpiController extends AbstractController<Epicure, Pimpam> {
	
	// Internal state ---------------------------------------------------------

			@Autowired
			protected PimpamEpiListService	listService;

			@Autowired
			protected PimpamEpiShowService	showService;
			

			// Constructors -----------------------------------------------------------


			@PostConstruct
			protected void initialise() {
				super.addCommand("list", this.listService);
				super.addCommand("show", this.showService);
			}

}
