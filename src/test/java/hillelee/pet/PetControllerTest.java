package hillelee.pet;

import com.jayway.jsonpath.JsonPath;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PetControllerTest {
	@Autowired
	PetJpnRepository repository;
	
	@Autowired
	MockMvc mockMvc;
	
	@After
	public void cleanup(){
		repository.deleteAll();
	}
	
	@Test
	public void getAllPets() {
		repository.save(
				new Pet("Tom", "cat", 3, LocalDate.now(),
						new MedicalCard(LocalDate.now(), "123")));
		
		
		try {
			mockMvc.perform(get("/pets"))
					.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
//			.andExpect(jsonPath("$.content", is(2)))
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}