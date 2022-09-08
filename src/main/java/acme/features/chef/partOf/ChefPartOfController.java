package acme.features.chef.partOf;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.partOf.PartOf;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefPartOfController extends AbstractController<Chef, PartOf> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefPartOfRecipeListService	listRecipesService;
	
	@Autowired
	protected ChefPartOfArtifactListService	listArtifactService;
	
	@Autowired
	protected ChefPartOfShowService showPartOfService;
	
	@Autowired
	protected ChefPartOfAddIngredientService addIngredientService;
	
	@Autowired
	protected ChefPartOfAddUtensilService addUtensilService;
	
	@Autowired
	protected ChefPartOfDeleteService deletePartOfService;
	
	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list-recipes", "list", this.listRecipesService);
		super.addCommand("list-artifacts", "list", this.listArtifactService);
		super.addCommand("show", this.showPartOfService);
		super.addCommand("add-ingredient","create", this.addIngredientService);
		super.addCommand("add-utensil","create", this.addUtensilService);
		super.addCommand("delete", this.deletePartOfService);
	}

}
