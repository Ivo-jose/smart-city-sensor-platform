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
    private String uid;
    private Double celsiusTemperature;
    private Instant utcTimesStamp;

    public TemperatureSensor() {}

    public TemperatureSensor(String uid, Double celsiusTemperature, Instant utcTimesStamp) {
        this.uid = uid;
        this.celsiusTemperature = celsiusTemperature;
        this.utcTimesStamp = utcTimesStamp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
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
	public int hashCode() {
		return Objects.hash(celsiusTemperature, uid, utcTimesStamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TemperatureSensor other = (TemperatureSensor) obj;
		return Objects.equals(celsiusTemperature, other.celsiusTemperature) && Objects.equals(uid, other.uid)
				&& Objects.equals(utcTimesStamp, other.utcTimesStamp);
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
