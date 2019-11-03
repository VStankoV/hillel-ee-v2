package hillelee.doctor;

import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class DoctorControllerTest {
	private DoctorController controller;
	
	@Before
	public void setUp() throws Exception {
		controller = new DoctorController();
	}
	
	@After
	public void tearDown() throws Exception {
		controller = null;
	}
	
	@Test
	public void createDoctor() {
		Integer doctorsCount = controller.getDoctorsCount();
		
		controller.createDoctor(
				new Doctor("Aibolit", "therapist"));
		
		assertThat(controller.getDoctorsCount(), is(doctorsCount + 1));
	}
	
	@Test
	public void getAllDoctors() {
		
		Doctor doctor1 = new Doctor("Who", "diagnosist");
		Doctor doctor2 = new Doctor("You", "diagnosist");
		Doctor doctor3 = new Doctor("Are", "diagnosist");
		
		controller.createDoctor(doctor1);
		controller.createDoctor(doctor2);
		controller.createDoctor(doctor3);
		
		assertThat(controller.getAllDoctors(), contains(doctor1, doctor2, doctor3));
	}
	
	@Test
	public void getDoctorById() {
		Doctor doctor = new Doctor("Watson", "surgeon");
		controller.createDoctor(doctor);
		
		assertThat(controller.getDoctorById(doctor.getId()), is(doctor));
	}
	
	@Test
	public void updateDoctor() {
		Doctor doctor = new Doctor("Watson", "surgeon");
		controller.createDoctor(doctor);
		
		Doctor newDoctor = new Doctor("Watson", "therapist");
		
		newDoctor.setId(doctor.getId());
		
		controller.updateDoctor(newDoctor);
		
		assertThat(controller.getDoctorById(newDoctor.getId()), is(newDoctor));
	}
	
	@Test
	public void deleteDoctor() {
		Doctor doctor = new Doctor("Watson", "surgeon");
		controller.createDoctor(doctor);
		
		controller.deleteDoctor(doctor);
		
		assertFalse(controller.getAllDoctors().contains(doctor));
	}
}