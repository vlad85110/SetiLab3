package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import reflection.Ignore;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private final float calvinToCelCost = 273;
    @JsonProperty
    private float temp;
    @Ignore
    @JsonProperty("temp_min")
    private float tempMin;
    @Ignore
    @JsonProperty("temp_max")
    private float tempMax;
    @JsonProperty("feels_like")
    private float feelsLike;

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp - calvinToCelCost;
    }

    public float getTempMin() {
        return tempMin;
    }

    public void setTempMin(float tempMin) {
        this.tempMin = tempMin - calvinToCelCost;
    }

    public float getTempMax() {
        return tempMax;
    }

    public void setTempMax(float tempMax) {
        this.tempMax = tempMax - calvinToCelCost;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(float feelsLike) {
        this.feelsLike = feelsLike - calvinToCelCost;
    }
}
