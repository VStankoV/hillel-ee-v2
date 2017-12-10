package hillelee;

import hillelee.pet.Pet;
import hillelee.pet.PetJpnRepository;
import hillelee.pet.PetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HilleleeConfig {
	@Bean
	PetService petService(PetJpnRepository petRepository) {
		return new PetService(petRepository);
	}
	
	@Bean
	CommandLineRunner initDB(PetJpnRepository repositiry) {
		return args -> {
//			repositiry.deleteAll();
			repositiry.save(
					new Pet("Tom", "cat", 3));
			
			repositiry.save(
					new Pet("Jerry", "mouse", 1));
			
			repositiry.save(
					new Pet("Jerry2", "mouse2", 12));
		};
	}
}
