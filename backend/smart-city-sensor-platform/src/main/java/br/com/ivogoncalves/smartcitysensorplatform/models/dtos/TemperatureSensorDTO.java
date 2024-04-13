package br.com.ivogoncalves.smartcitysensorplatform.models.dtos;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class TemperatureSensorDTO implements Serializable {
    
	private static final long serialVersionUID = 1L;

    private Long uid;
    private Double celsiusTemperature;
    private Instant utcTimesStamp;

    public TemperatureSensorDTO() {}

    public TemperatureSensorDTO(Long uid, Double celsiusTemperature, Instant utcTimesStamp) {
        this.uid = uid;
        this.celsiusTemperature = celsiusTemperature;
        this.utcTimesStamp = utcTimesStamp;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Double getCelsiusTemperature() {
        return celsiusTemperature;
    }

    public void setCelsiusTemperature(Double celsiusTemperature) {
        this.celsiusTemperature = celsiusTemperature;
    }

    public Instant getUtcTimesStamp() {
        return utcTimesStamp;
    }

    public void setUtcTimesStamp(Instant utcTimesStamp) {
        this.utcTimesStamp = utcTimesStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemperatureSensorDTO that = (TemperatureSensorDTO) o;
        return Objects.equals(uid, that.uid) && Objects.equals(celsiusTemperature, that.celsiusTemperature)
                && Objects.equals(utcTimesStamp, that.utcTimesStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, celsiusTemperature, utcTimesStamp);
    }

    @Override
    public String toString() {
        return """
                TemperatureSensor{
                   uid: %d,
                   celsiusTemperature: %.2f,
                   utcTimesStamp: %s
                }
                """.formatted(getUid(),getCelsiusTemperature(), getUtcTimesStamp().toString());
    }
}
