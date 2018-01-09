package hillelee.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pet {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String specie;
	private Integer age;
	private LocalDate birthDate;
	@OneToOne(cascade = CascadeType.ALL)
	private MedicalCard medicalCard;
	
	public Pet(String name, String specie, Integer age, LocalDate birthDate, MedicalCard medicalCard) {
		this.name = name;
		this.specie = specie;
		this.age = age;
		this.birthDate = birthDate;
		this.medicalCard = medicalCard;
	}
}
