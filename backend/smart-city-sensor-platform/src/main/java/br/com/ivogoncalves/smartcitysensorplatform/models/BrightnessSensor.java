package br.com.ivogoncalves.smartcitysensorplatform.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Document(collection = "brightness-sensor")
public class BrightnessSensor implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
    @Id
    private String uid;
    private Double lux;
    private Instant timesStampUTC;

    public BrightnessSensor() {}

    public BrightnessSensor(String uid, Double lux, Instant timesStampUTC) {
        this.uid = uid;
        this.lux = lux;
        this.timesStampUTC = timesStampUTC;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Double getLux() {
        return lux;
    }

    public void setLux(Double lux) {
        this.lux = lux;
    }

    public Instant getTimesStampUTC() {
        return timesStampUTC;
    }

    public void setTimesStampUTC(Instant timesStampUTC) {
        this.timesStampUTC = timesStampUTC;
    }

    @Override
	public int hashCode() {
		return Objects.hash(lux, timesStampUTC, uid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BrightnessSensor other = (BrightnessSensor) obj;
		return Objects.equals(lux, other.lux) && Objects.equals(timesStampUTC, other.timesStampUTC)
				&& Objects.equals(uid, other.uid);
	}

	@Override
    public String toString() {
        return """
                BrightnessSensor{" +
                    uid: %d,
                    lux: %.2f,
                    timesStampUTC: %s
                }
                """.formatted(getUid(),getLux(),getTimesStampUTC().toString());
    }
}
