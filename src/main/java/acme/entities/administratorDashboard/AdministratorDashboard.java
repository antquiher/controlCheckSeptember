package acme.entities.administratorDashboard;

import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.artifact.ArtifactType;
import acme.entities.fineDish.StatusType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	protected Map<ArtifactType,Integer>					totalArtifact;
	
	protected Map<Pair<ArtifactType, String>, Double>	averageArtifactRetailPrice;
	
	protected Map<Pair<ArtifactType, String>, Double>	deviationArtifactRetailPrice;
	
	protected Map<Pair<ArtifactType, String>, Double>	minimumArtifactRetailPrice;

	protected Map<Pair<ArtifactType, String>, Double>	maximumArtifactRetailPrice;
	
	protected Map<StatusType, Integer> 					totalFineDish;
	
	protected Map<StatusType, Double>					averageFineDishBudget;
	
	protected Map<StatusType, Double>					deviationFineDishBudget;
	
	protected Map<StatusType, Double>					maximumFineDishBudget;
	
	protected Map<StatusType, Double>					minimumFineDishBudget;
	
	//PIMPAM
	protected Double								ratioOfArtifactsWithPimpam;
	
	protected Map<String, Double>					averagePimpamBudget;
	
	protected Map<String, Double>					deviationPimpamBudget;
	
	protected Map<String, Double>					maximumPimpamBudget;
	
	protected Map<String, Double>					minimumPimpamBudget;
	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
		
}
