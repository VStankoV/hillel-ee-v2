package hillelee;

import hillelee.pet.MedicalCard;
import hillelee.pet.Pet;
import hillelee.pet.PetJpnRepository;
import hillelee.pet.PetService;
import hillelee.store.Medicine;
import hillelee.store.MedicineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@Configuration
public class HilleleeConfig {
	@Bean
	PetService petService(PetJpnRepository petRepository) {
		return new PetService(petRepository);
	}
	
	@Bean
	@Profile("prod")
	CommandLineRunner initDB(PetJpnRepository repositiry) {
		return args -> {
//			repositiry.deleteAll();
			repositiry.save(
					new Pet("Tom", "cat", 3, LocalDate.now(),
							new MedicalCard(LocalDate.now(), "123")));
			
			repositiry.save(
					new Pet("Jerry", "mouse", 1, LocalDate.now(),
							new MedicalCard(LocalDate.now(), "456")));
			
		};
	}
	
	@Bean
	@Profile("prod")
	CommandLineRunner initMedicineStore(MedicineRepository repository) {
		return args -> {
			repository.save(new Medicine("Zelenka", 10));
		};
	}
}
