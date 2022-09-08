package acme.entities.recipe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import acme.roles.Chef;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Recipe extends AbstractEntity {
	
	protected static final long	serialVersionUID	= 1L;
	
	@Column(unique=true)
	@Pattern(regexp = "^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$")
	protected String code;
	
	@NotBlank
	@Length(max=101)
	protected String heading;
	
	@NotBlank
	@Length(max=256)
	protected String description;
	
	@NotBlank
	@Length(max=256)
	protected String preparationNotes;
	
	@URL
	protected String link;
	
	@NotNull
	protected boolean isPublished;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Chef chef;

}
