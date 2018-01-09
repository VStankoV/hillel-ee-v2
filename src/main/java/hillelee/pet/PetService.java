package hillelee.pet;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//@Service
@RequiredArgsConstructor
public class PetService {
	private final PetJpnRepository petRepository;
	
	
	public List<Pet> getPets(Optional<String> specie, Pageable pageable) {
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
		Optional<Pet> optionalPet = petRepository.findById(id);
		optionalPet.ifPresent(pet -> petRepository.delete(pet.getId()));
		return optionalPet;
	}
}
