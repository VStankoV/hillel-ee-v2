package hillelee.clinic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ClinicController {
	private final ClinicInfo clinicInfo;
	
	@GetMapping("clinic/info")
	public ClinicInfo clinicInfo() {
		return clinicInfo;
	}
	
	
}

@Data
@Component
@ConfigurationProperties("clinic")
class ClinicInfo {
	private String name;
	private String workingHours;
}
