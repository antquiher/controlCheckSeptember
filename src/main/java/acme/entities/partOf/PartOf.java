package acme.entities.partOf;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import acme.entities.artifact.Artifact;
import acme.entities.recipe.Recipe;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PartOf extends AbstractEntity {
	
	protected static final long	serialVersionUID	= 1L;
	
	@NotNull
	@Positive
	protected Double quantity;
	
	protected String unit;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Recipe recipe;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Artifact artifact;

}
