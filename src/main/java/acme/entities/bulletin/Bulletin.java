package acme.entities.bulletin;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Bulletin extends AbstractEntity{

	protected static final long serialVersionUID= 1L;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date instantiationMoment;
	
	@Length(min=1,max=100)
	@NotBlank
	protected String heading;
	
	
	@Length(min=1,max=255)
	@NotBlank
	protected String text;
	
	
	protected boolean flag;
	
	@URL
	protected String link;
}
