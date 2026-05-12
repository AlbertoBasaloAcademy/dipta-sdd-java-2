package academy.aicode.astrobookings.repository;

import academy.aicode.astrobookings.model.Launch;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class LaunchRepository {
    private final Map<UUID, Launch> launches = new ConcurrentHashMap<>();

    public List<Launch> findAll() {
        return new ArrayList<>(launches.values());
    }

    public Optional<Launch> findById(UUID id) {
        return Optional.ofNullable(launches.get(id));
    }

    public Launch save(Launch launch) {
        if (launch.getId() == null) {
            launch.setId(UUID.randomUUID());
        }
        launches.put(launch.getId(), launch);
        return launch;
    }

    public void deleteById(UUID id) {
        launches.remove(id);
    }
}
