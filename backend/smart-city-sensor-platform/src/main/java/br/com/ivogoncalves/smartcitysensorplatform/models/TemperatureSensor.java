package br.com.ivogoncalves.smartcitysensorplatform.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Document(collection = "temperature-sensor")
public class TemperatureSensor implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
    @Id
    private Long uid;
    private Double celsiusTemperature;
    private Instant utcTimesStamp;

    public TemperatureSensor() {}

    public TemperatureSensor(Long uid, Double celsiusTemperature, Instant utcTimesStamp) {
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
        TemperatureSensor that = (TemperatureSensor) o;
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
