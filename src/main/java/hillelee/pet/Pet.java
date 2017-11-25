package hillelee.pet;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pet {
	private String name;
	private String specie;
	private Integer age;
}
