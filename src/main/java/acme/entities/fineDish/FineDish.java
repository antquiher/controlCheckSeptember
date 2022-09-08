package acme.entities.fineDish;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Chef;
import acme.roles.Epicure;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FineDish extends AbstractEntity{
	
	
	// Serialisation identifier -----------------------------------------------

	protected static final long		serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------
	
	@NotNull
	protected StatusType status;
	
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$")
	protected String code;
	
	@NotBlank
	@Length(min=1,max=256)
	protected String request;
	
	@NotNull
	protected Money budget;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date initialDate;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date finishDate;
	
	@URL
	protected String url;
	
	@NotNull
	protected boolean isPublish;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Chef chef;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Epicure epicure;
}
