package br.com.ivogoncalves.smartcitysensorplatform.models.dtos;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class BrightnessSensorDTO implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
    private Long uid;
    private Double lux;
    private Instant timesStampUTC;

    public BrightnessSensorDTO() {}

    public BrightnessSensorDTO(Long uid, Double lux, Instant timesStampUTC) {
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
        BrightnessSensorDTO that = (BrightnessSensorDTO) o;
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
