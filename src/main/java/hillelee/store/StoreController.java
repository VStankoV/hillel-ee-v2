package hillelee.store;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class StoreController {
	private final StoreService service;
	
	@GetMapping("/medicine-store")
	public List<Medicine> getAll() {
		return service.findAll();
	}
	
	@PostMapping("/medicine-store")
	public void add(@RequestBody Medicine medicine) {
		service.add(medicine.getName(), medicine.getQuantity());
	}
	
}
