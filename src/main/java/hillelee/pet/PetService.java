package hillelee.pet;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//@Service
@RequiredArgsConstructor
public class PetService {
	private final PetRepository petRepository;
	
	
	public List<Pet> getPets(Optional<String> specie) {
		Predicate<Pet> specieFilter = specie.map(this::filterBySpecie)
				.orElse(pet -> true);
		
		return petRepository.findAll().stream()
				.filter(specieFilter)
				.collect(Collectors.toList());
	}
	
	private Predicate<Pet> filterBySpecie(String specie) {
		return pet -> pet.getSpecie().equals(specie);
	}
	
	public Optional<Pet> getPetById(Integer id) {
		return petRepository.findById(id);
	}
	
	public Pet save(Pet pet) {
		return petRepository.save(pet);
	}
	
	public Optional<Pet> delete(Integer id) {
		return petRepository.delete(id);
	}
}
