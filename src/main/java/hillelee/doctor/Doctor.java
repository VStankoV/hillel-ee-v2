package hillelee.doctor;

import lombok.Data;

@Data
public class Doctor {
	private Integer id;
	private final String name;
	private String specialization;
	
	public Doctor(String name, String specialization) {
		this.name = name;
		this.specialization = specialization;
	}
}
