package acme.entities.delor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Transient;

import acme.entities.artifact.Artifact;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delor extends AbstractEntity{
protected static final long		serialVersionUID	= 1L;
	
	//Attributes patronage

	
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^\\d{6}:[0-9]{2}[0-9]{2}[0-9]{2}$")
	protected String code;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	protected Date instantiationMoment;
	
	
	@NotBlank
	@Length(min=1,max=100)
	protected String title;
	
	@NotBlank
	@Length(min=1,max=255)
	protected String description;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date startPeriod;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date finishPeriod;
	
	@NotNull
	protected Money budget;
	
	@URL
	protected String link;

	@Transient
	protected String auxiliarSeisNumeros;
	
	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@OneToOne(optional = false)
	protected Artifact artifact;
}
