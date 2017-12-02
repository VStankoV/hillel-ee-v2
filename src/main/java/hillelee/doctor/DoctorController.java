package hillelee.doctor;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class DoctorController {
	AtomicInteger counter = new AtomicInteger();
	
	private List<Doctor> doctors = new ArrayList<>();
	
	public void createDoctor(Doctor doctor) {
		int id = counter.getAndIncrement();
		doctor.setId(id);
		doctors.add(doctor);
	}
	
	public List<Doctor> getAllDoctors() {
		return doctors;
	}
	
	public Doctor getDoctorById(Integer id) {
		return doctors.get(id);
		
	}
	
	public void updateDoctor(Doctor doctor) {
		if (doctor.getId() != null) {
			doctors.set(doctor.getId(), doctor);
		} else {
			throw new RuntimeException();
		}
	}
	
	public void deleteDoctor(Doctor doctor) {
		doctors.remove(doctor);
	}
	
	public Integer getDoctorsCount() {
		return doctors.size();
	}
}
