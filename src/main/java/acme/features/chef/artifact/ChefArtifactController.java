package acme.features.chef.artifact;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.artifact.Artifact;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefArtifactController extends AbstractController<Chef, Artifact> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefIngredientListService	listIngredientService;
	
	@Autowired
	protected ChefUtensilListService	listUtensilService;
	
	@Autowired
	protected ChefArtifactShowService showArtifactService;
	
	@Autowired 
	protected ChefIngredientCreateService createIngredientService;
	
	@Autowired 
	protected ChefUtensilCreateService createUtensilService;
	
	@Autowired
	protected ChefArtifactUpdateService updateArtifactService;
	
	@Autowired
	protected ChefArtifactDeleteService deleteArtifactService;
	
	@Autowired
	protected ChefArtifactPublishService publishArtifactService;
	
	
	
	

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("create-utensil", "create", this.createUtensilService);
		super.addCommand("list-ingredient","list", this.listIngredientService);
		super.addCommand("list-utensil","list", this.listUtensilService);
		super.addCommand("show", this.showArtifactService);
		super.addCommand("create-ingredient", "create", this.createIngredientService);
		super.addCommand("update", this.updateArtifactService);
		super.addCommand("delete", this.deleteArtifactService);
		super.addCommand("publish", "update", this.publishArtifactService);
	}

}
