package acme.features.chef.recipes;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.recipe.Recipe;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefRecipeController extends AbstractController<Chef, Recipe> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefRecipeListService	listRecipeService;
	
	@Autowired
	protected ChefRecipeShowService showRecipeService;
	
	@Autowired
	protected ChefRecipeCreateService createRecipeService;
	
	@Autowired
	protected ChefRecipeDeleteService deleteRecipeService;

	@Autowired
	protected ChefRecipeUpdateService updateRecipeService;
	
	@Autowired
	protected ChefRecipePublishService publishRecipeService;
	
	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listRecipeService);
		super.addCommand("show", this.showRecipeService);
		super.addCommand("create", this.createRecipeService);
		super.addCommand("delete", this.deleteRecipeService);
		super.addCommand("update", this.updateRecipeService);
		super.addCommand("publish","update", this.publishRecipeService);
		
	}

}
