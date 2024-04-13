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
    private Long uid;
    private Double lux;
    private Instant timesStampUTC;

    public BrightnessSensor() {}

    public BrightnessSensor(Long uid, Double lux, Instant timesStampUTC) {
        this.uid = uid;
        this.lux = lux;
        this.timesStampUTC = timesStampUTC;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrightnessSensor that = (BrightnessSensor) o;
        return Objects.equals(uid, that.uid) && Objects.equals(lux, that.lux) && Objects.equals(timesStampUTC, that.timesStampUTC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, lux, timesStampUTC);
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
