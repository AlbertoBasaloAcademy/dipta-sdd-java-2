package academy.aicode.astrobookings.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Launch {
    private UUID id;

    @NotNull(message = "Rocket ID is required")
    private UUID rocketId;

    @NotNull(message = "Launch date is required")
    private LocalDateTime launchDate;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be at least 0")
    private BigDecimal price;

    @NotNull(message = "Minimum occupancy is required")
    @Min(value = 1, message = "Minimum occupancy must be at least 1")
    private Integer minOccupancy;

    private LaunchStatus status;

    public Launch() {}

    public Launch(UUID id, UUID rocketId, LocalDateTime launchDate, BigDecimal price, Integer minOccupancy, LaunchStatus status) {
        this.id = id;
        this.rocketId = rocketId;
        this.launchDate = launchDate;
        this.price = price;
        this.minOccupancy = minOccupancy;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRocketId() {
        return rocketId;
    }

    public void setRocketId(UUID rocketId) {
        this.rocketId = rocketId;
    }

    public LocalDateTime getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDateTime launchDate) {
        this.launchDate = launchDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getMinOccupancy() {
        return minOccupancy;
    }

    public void setMinOccupancy(Integer minOccupancy) {
        this.minOccupancy = minOccupancy;
    }

    public LaunchStatus getStatus() {
        return status;
    }

    public void setStatus(LaunchStatus status) {
        this.status = status;
    }
}
