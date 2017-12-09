package hillelee.pet;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@Entity
public class Pet {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String specie;
	private Integer age;
}
