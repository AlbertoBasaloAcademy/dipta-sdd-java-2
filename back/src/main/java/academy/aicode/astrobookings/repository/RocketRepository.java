package academy.aicode.astrobookings.repository;

import academy.aicode.astrobookings.model.Rocket;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class RocketRepository {
    private final Map<UUID, Rocket> rockets = new ConcurrentHashMap<>();

    public List<Rocket> findAll() {
        return new ArrayList<>(rockets.values());
    }

    public Optional<Rocket> findById(UUID id) {
        return Optional.ofNullable(rockets.get(id));
    }

    public Rocket save(Rocket rocket) {
        if (rocket.getId() == null) {
            rocket.setId(UUID.randomUUID());
        }
        rockets.put(rocket.getId(), rocket);
        return rocket;
    }

    public void deleteById(UUID id) {
        rockets.remove(id);
    }
}
