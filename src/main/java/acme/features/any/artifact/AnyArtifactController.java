package acme.features.any.artifact;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.artifact.Artifact;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyArtifactController extends AbstractController<Any, Artifact> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyIngredientListService	listIngredientService;
	
	@Autowired
	protected AnyUtensilListService	listUtensilService;
	
	@Autowired
	protected AnyArtifactShowService showArtifactService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list-ingredient","list", this.listIngredientService);
		super.addCommand("list-utensil","list", this.listUtensilService);
		super.addCommand("show", this.showArtifactService);
	}

}
