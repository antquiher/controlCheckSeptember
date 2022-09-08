package acme.entities.peep;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Peep extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------

		protected static final long		serialVersionUID	= 1L;
		
		//Attributes Peep
		
		@NotNull
		@Past
		@Temporal(TemporalType.TIMESTAMP)
		protected Date instantiationMoment;
		
		@NotBlank
		@Length(min=1,max=101)
		protected String heading;
		
		@NotBlank
		@Length(min=1,max=101)
		protected String writer;
		
		@NotBlank
		@Length(min=1,max=256)
		protected String text;
		
		@Email
		protected String email;

}

