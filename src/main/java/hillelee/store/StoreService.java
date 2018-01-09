package hillelee.store;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StoreService {
	private final MedicineRepository repository;
	
	public List<Medicine> findAll() {
		return repository.findAll();
	}
	
	public void add(String name, Integer quantity) {
		Optional<Medicine> mayBeMedicine = repository.findByName(name);
		
		Medicine medicine = mayBeMedicine.orElseGet(() -> new Medicine(name, 0));
		
		medicine.setQuantity(medicine.getQuantity() + quantity);
		
		repository.save(medicine);
	}
}
