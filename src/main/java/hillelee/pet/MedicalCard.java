package hillelee.pet;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class MedicalCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate firstAppearance;
	private String complaints;
	
	public MedicalCard(LocalDate firstAppearance, String complaints) {
		this.firstAppearance = firstAppearance;
		this.complaints = complaints;
	}
}
