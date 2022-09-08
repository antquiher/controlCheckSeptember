package acme.entities.systemSetting;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemSettings extends AbstractEntity{

	
	 //Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;
				
		
		
		//Attributes
		protected double spamThreshold=0.10;
		
		@NotBlank
		protected String defaultCurrency;
		
		@NotBlank
		protected String acceptedCurrencies;
		
		@NotBlank
		protected String spamTuples;
		
		@Transient
		public Map<String, Double> getSpamTuplesFormatted() {
			Map<String, Double> spamTuplesFormatted = new HashMap<>();
			final String[] strongWords = spamTuples.substring(1,spamTuples.length()-1).toLowerCase().split("\\), \\(");
	        for(int i =0; i<strongWords.length;i++) {
	        	String[] tuple = strongWords[i].split(", ");
	        	String word = tuple[0];
	        	Double weight = Double.parseDouble(tuple[1]);
	            spamTuplesFormatted.put(word, weight);
	        }
			return spamTuplesFormatted;
		}
}
