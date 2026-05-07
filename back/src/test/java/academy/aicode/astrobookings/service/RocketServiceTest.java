package academy.aicode.astrobookings.service;

import academy.aicode.astrobookings.model.Rocket;
import academy.aicode.astrobookings.model.RocketRange;
import academy.aicode.astrobookings.repository.RocketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RocketServiceTest {

    private RocketRepository repository;
    private RocketService service;

    @BeforeEach
    void setUp() {
        repository = new RocketRepository();
        service = new RocketService(repository);
    }

    @Test
    void shouldCreateRocket() {
        Rocket rocket = new Rocket(null, "Falcon 9", 5, RocketRange.Earth, false);
        Rocket created = service.createRocket(rocket);

        assertNotNull(created.getId());
        assertEquals("Falcon 9", created.getName());
        assertFalse(created.isDecommissioned());
    }

    @Test
    void shouldThrowExceptionWhenCapacityIsInvalid() {
        Rocket rocket = new Rocket(null, "Big Rocket", 10, RocketRange.Mars, false);
        assertThrows(IllegalArgumentException.class, () -> service.createRocket(rocket));
    }

    @Test
    void shouldUpdateRocket() {
        Rocket rocket = new Rocket(null, "Falcon 9", 5, RocketRange.Earth, false);
        Rocket created = service.createRocket(rocket);

        Rocket details = new Rocket(null, "Falcon Heavy", 9, RocketRange.Mars, false);
        Rocket updated = service.updateRocket(created.getId(), details);

        assertEquals("Falcon Heavy", updated.getName());
        assertEquals(9, updated.getCapacity());
        assertEquals(RocketRange.Mars, updated.getRange());
    }

    @Test
    void shouldDecommissionRocket() {
        Rocket rocket = new Rocket(null, "Falcon 9", 5, RocketRange.Earth, false);
        Rocket created = service.createRocket(rocket);

        service.decommissionRocket(created.getId());
        Rocket updated = service.getRocketById(created.getId()).get();

        assertTrue(updated.isDecommissioned());
    }

    @Test
    void shouldGetAllRockets() {
        service.createRocket(new Rocket(null, "R1", 1, RocketRange.Earth, false));
        service.createRocket(new Rocket(null, "R2", 2, RocketRange.Moon, false));

        List<Rocket> all = service.getAllRockets();
        assertEquals(2, all.size());
    }
}
