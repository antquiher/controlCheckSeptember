package acme.features.administrator.systemSetting;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.systemSetting.SystemSettings;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;

@Controller
public class AdministratorSystemSettingsController extends AbstractController<Administrator, SystemSettings> {

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected AdministratorSystemSettingsService showService;
	
	@Autowired
	protected AdministratorSystemSettingsUpdateService updateService;
	
	// Constructors -----------------------------------------------------------
	
		@PostConstruct
		protected void initialise() {
			super.addCommand("show", this.showService);
			super.addCommand("update", this.updateService);
		}
	
}
