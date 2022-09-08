package acme.features.any.partOf;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.partOf.PartOf;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyPartOfController extends AbstractController<Any, PartOf> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyPartOfRecipeListService	listRecipesService;
	
	@Autowired
	protected AnyPartOfArtifactListService	listArtifactService;
	
	@Autowired
	protected AnyPartOfShowService showPartOfService;
	
	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list-recipes", "list", this.listRecipesService);
		super.addCommand("list-artifacts", "list", this.listArtifactService);
		super.addCommand("show", this.showPartOfService);
	}

}
