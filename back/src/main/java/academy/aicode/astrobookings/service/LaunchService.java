package academy.aicode.astrobookings.service;

import academy.aicode.astrobookings.model.Launch;
import academy.aicode.astrobookings.model.LaunchStatus;
import academy.aicode.astrobookings.repository.LaunchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LaunchService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(LaunchService.class);
    private final LaunchRepository repository;
    private final RocketService rocketService;

    public LaunchService(LaunchRepository repository, RocketService rocketService) {
        this.repository = repository;
        this.rocketService = rocketService;
    }

    public Launch createLaunch(Launch launch) {
        validateLaunch(launch);
        
        rocketService.getRocketById(launch.getRocketId())
                .orElseThrow(() -> new IllegalArgumentException("Rocket not found with id: " + launch.getRocketId()));
        
        launch.setId(null);
        launch.setStatus(LaunchStatus.Created);
        Launch savedLaunch = repository.save(launch);
        LOGGER.info("Launch created: {} for rocket {} on {}", savedLaunch.getId(), savedLaunch.getRocketId(), savedLaunch.getLaunchDate());
        return savedLaunch;
    }

    public List<Launch> getAllLaunches() {
        return repository.findAll();
    }

    public Launch getLaunchById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Launch not found with id: " + id));
    }

    public Launch updateLaunchStatus(UUID id, LaunchStatus status) {
        Launch launch = getLaunchById(id);
        LaunchStatus oldStatus = launch.getStatus();
        launch.setStatus(status);
        Launch updatedLaunch = repository.save(launch);
        LOGGER.info("Launch {} status updated from {} to {}", id, oldStatus, status);
        return updatedLaunch;
    }

    private void validateLaunch(Launch launch) {
        if (launch.getRocketId() == null) {
            throw new IllegalArgumentException("Rocket ID is required");
        }
        if (launch.getLaunchDate() == null) {
            throw new IllegalArgumentException("Launch date is required");
        }
        if (launch.getPrice() == null || launch.getPrice().doubleValue() < 0) {
            throw new IllegalArgumentException("Price must be at least 0");
        }
        if (launch.getMinOccupancy() == null || launch.getMinOccupancy() < 1) {
            throw new IllegalArgumentException("Minimum occupancy must be at least 1");
        }
    }
}
