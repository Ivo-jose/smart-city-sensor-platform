package br.com.ivogoncalves.smartcitysensorplatform.models.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

public class TemperatureSensorDTO extends RepresentationModel<TemperatureSensorDTO> implements Serializable {
    
	private static final long serialVersionUID = 1L;

    private String uid;
    private Double celsiusTemperature;
    private Date utcTimesStamp;

    public TemperatureSensorDTO() {}

    public TemperatureSensorDTO(String uid, Double celsiusTemperature, Date utcTimesStamp) {
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

    public Date getUtcTimesStamp() {
        return utcTimesStamp;
    }

    public void setUtcTimesStamp(Date utcTimesStamp) {
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
		TemperatureSensorDTO other = (TemperatureSensorDTO) obj;
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
