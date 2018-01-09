package hillelee.pet.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PrescriptionInputDTO {
	
	@Min(1)
	@Max(12)
	@NotNull
	Integer timesPerDate;
	
	@NotEmpty
	String medicineName;
	
	@Future
	LocalDate start;
}
