package acme.features.any.recipes;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.recipe.Recipe;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyRecipeController extends AbstractController<Any, Recipe> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyRecipeListService	listUtensilService;
	
	@Autowired
	protected AnyRecipeShowService showArtifactService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listUtensilService);
		super.addCommand("show", this.showArtifactService);
	}

}
