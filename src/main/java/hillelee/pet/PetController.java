package hillelee.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class PetController {
	private final PetService petService;
	
	
	//	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	@GetMapping("/greeting")
	public String helloworld() {
		return "Hello world";
	}
	
	@GetMapping("/pets")
	public List<Pet> getPets(@RequestParam(required = false) Optional<String> specie) {
		return petService.getPets(specie);
	}
	
	@GetMapping("/pets/{id}")
	public ResponseEntity<?> getPetById(@PathVariable Integer id) {
		Optional<Pet> mayBePet = petService.getPetById(id);
		
		return mayBePet.map(Object.class::cast)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.badRequest()
						.body(new ErrorBody("Not found " + id)));
	}
	
	@PostMapping("/pets")
	public ResponseEntity<Void> cratePet(@RequestBody Pet pet) {
		Pet saved = petService.save(pet);
		return ResponseEntity.created(URI.create("/pets/" + saved.getId())).build();
	}
	
	@PutMapping("/pets/{id}")
	public void updatePet(@PathVariable Integer id, @RequestBody Pet pet) {
		pet.setId(id);
		petService.save(pet);
	}
	
	@DeleteMapping("/pets/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePet(@PathVariable Integer id) {
		petService.delete(id)
				.orElseThrow(NoSuchPetException::new);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private class NoSuchPetException extends RuntimeException {
	}
	
	@Data
	@AllArgsConstructor
	private class ErrorBody {
		private final Integer code = 400;
		private String message;
	}
}
