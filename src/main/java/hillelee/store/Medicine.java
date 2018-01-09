package hillelee.store;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

enum Type {

}

@Data
@NoArgsConstructor
@Entity
public class Medicine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer quantity;
	@Enumerated(EnumType.STRING)
	Type type;
	
	public Medicine(String name, Integer quantity) {
		this.name = name;
		this.quantity = quantity;
	}
}
