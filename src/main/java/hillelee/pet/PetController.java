package hillelee.pet;

import com.sun.xml.internal.ws.server.ServerRtException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class PetController {
	
	private Map<Integer, Pet> pets = new HashMap<Integer, Pet>() {{
		put(0, new Pet("Tom", "cat", 3));
		put(1, new Pet("Jerry", "mouse", 1));
	}};
	
	private Integer counter = 1;
	
	//	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	@GetMapping("/greeting")
	
	public String helloworld() {
		return "Hello world";
	}
	
	
	@GetMapping("/pets")
	public List<Pet> getPets(@RequestParam(required = false) Optional<String> specie) {
		
		Predicate<Pet> specieFilter = specie.map(this::filterBySpecie)
				.orElse(pet -> true);
		
		return pets.values().stream()
				.filter(specieFilter)
				.collect(Collectors.toList());
		
	}
	
	private Predicate<Pet> filterBySpecie(String specie) {
		return pet -> pet.getSpecie().equals(specie);
	}
	
	
	@GetMapping("/pets/{id}")
	public ResponseEntity<?> getPetById(@PathVariable Integer id) {
		if (id >= pets.size()) {
			return ResponseEntity.badRequest()
					.body(new ErrorBody("Not found " + id));
		}
		
		return ResponseEntity.ok(pets.get(id));
	}
	
	@PostMapping("/pets")
	public ResponseEntity<Void> cratePet(@RequestBody Pet pet) {
		pets.put(++counter, pet);
		return ResponseEntity.created(URI.create("/pets/" + counter)).build();
	}
	
	@PutMapping("/pets/{id}")
	public void updatePet(@PathVariable Integer id, @RequestBody Pet pet) {
		pets.put(id, pet);
	}
	
	@DeleteMapping("/pets/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePet(@PathVariable Integer id) {
		if (!pets.containsKey(id)) {
			throw new NoSuchPetException();
		}
		pets.remove(id);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private class NoSuchPetException extends RuntimeException {
	}
}

@Data
@AllArgsConstructor
class ErrorBody {
	private final Integer code = 400;
	private String message;
}
