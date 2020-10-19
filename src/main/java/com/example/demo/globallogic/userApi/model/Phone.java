package com.example.demo.globallogic.userApi.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "number",
        "citycode",
        "Countrycode"
})
public class Phone {

    @JsonProperty("number")
    private String number;
    @JsonProperty("citycode")
    private String citycode;
    @JsonProperty("countrycode")
    private String countrycode;

    @JsonProperty("number")
    public String getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(String number) {
        this.number = number;
    }

    @JsonProperty("citycode")
    public String getCitycode() {
        return citycode;
    }

    @JsonProperty("citycode")
    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    @JsonProperty("Countrycode")
    public String getCountrycode() {
        return countrycode;
    }

    @JsonProperty("Countrycode")
    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }



}
