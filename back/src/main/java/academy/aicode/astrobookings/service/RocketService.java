package academy.aicode.astrobookings.service;

import academy.aicode.astrobookings.model.Rocket;
import academy.aicode.astrobookings.repository.RocketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RocketService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(RocketService.class);
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
        Rocket savedRocket = repository.save(rocket);
        LOGGER.info("Rocket created: {} (Name: {}, Capacity: {})", savedRocket.getId(), savedRocket.getName(), savedRocket.getCapacity());
        return savedRocket;
    }

    public Rocket updateRocket(UUID id, Rocket rocketDetails) {
        Rocket rocket = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rocket not found with id: " + id));
        
        validateRocket(rocketDetails);
        
        rocket.setName(rocketDetails.getName());
        rocket.setCapacity(rocketDetails.getCapacity());
        rocket.setRange(rocketDetails.getRange());
        
        Rocket updatedRocket = repository.save(rocket);
        LOGGER.info("Rocket updated: {}", updatedRocket.getId());
        return updatedRocket;
    }

    public void decommissionRocket(UUID id) {
        Rocket rocket = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rocket not found with id: " + id));
        rocket.setDecommissioned(true);
        repository.save(rocket);
        LOGGER.info("Rocket decommissioned: {}", id);
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
