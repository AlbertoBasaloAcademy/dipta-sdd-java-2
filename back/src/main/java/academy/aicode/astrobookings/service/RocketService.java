package academy.aicode.astrobookings.service;

import academy.aicode.astrobookings.model.Rocket;
import academy.aicode.astrobookings.repository.RocketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RocketService {
    private final RocketRepository repository;

    public RocketService(RocketRepository repository) {
        this.repository = repository;
    }

    public List<Rocket> getAllRockets() {
        return repository.findAll();
    }

    public Optional<Rocket> getRocketById(UUID id) {
        return repository.findById(id);
    }

    public Rocket createRocket(Rocket rocket) {
        validateRocket(rocket);
        rocket.setId(null); // Ensure it's a new rocket
        rocket.setDecommissioned(false);
        return repository.save(rocket);
    }

    public Rocket updateRocket(UUID id, Rocket rocketDetails) {
        Rocket rocket = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rocket not found with id: " + id));
        
        validateRocket(rocketDetails);
        
        rocket.setName(rocketDetails.getName());
        rocket.setCapacity(rocketDetails.getCapacity());
        rocket.setRange(rocketDetails.getRange());
        
        return repository.save(rocket);
    }

    public void decommissionRocket(UUID id) {
        Rocket rocket = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rocket not found with id: " + id));
        rocket.setDecommissioned(true);
        repository.save(rocket);
    }

    private void validateRocket(Rocket rocket) {
        if (rocket.getCapacity() < 1 || rocket.getCapacity() > 9) {
            throw new IllegalArgumentException("Capacity must be between 1 and 9");
        }
        if (rocket.getName() == null || rocket.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (rocket.getRange() == null) {
            throw new IllegalArgumentException("Range is required");
        }
    }
}
