package hillelee.pet;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetJpnRepository extends JpaRepository<Pet, Integer> {
	public Optional<Pet> findById(Integer id);
	
	
}
