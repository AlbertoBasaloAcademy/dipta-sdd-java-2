package academy.aicode.astrobookings.controller;

import academy.aicode.astrobookings.model.Launch;
import academy.aicode.astrobookings.model.LaunchStatus;
import academy.aicode.astrobookings.service.LaunchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/launches")
@CrossOrigin(origins = "*")
public class LaunchController {
    private final LaunchService service;

    public LaunchController(LaunchService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Launch createLaunch(@Valid @RequestBody Launch launch) {
        return service.createLaunch(launch);
    }

    @GetMapping
    public List<Launch> getAllLaunches() {
        return service.getAllLaunches();
    }

    @GetMapping("/{id}")
    public Launch getLaunchById(@PathVariable UUID id) {
        return service.getLaunchById(id);
    }

    @PatchMapping("/{id}/status")
    public Launch updateLaunchStatus(@PathVariable UUID id, @RequestBody LaunchStatus status) {
        return service.updateLaunchStatus(id, status);
    }
}
