package com.example.countries.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.HashMap;

@NoArgsConstructor
@Data
public class Country {
    @JsonProperty(value = "cca3")
    private String countryCode;
    private HashMap<String, String> languages;
    private long population;
    private HashMap<String, Currency> currencies;
    @JsonProperty(value = "capital")
    private String[] capitals;
    @JsonProperty(value = "latlng")
    private Point2D coordinates;
    public void setCoordinates(Float[] coordinates){
        //longitude values are considered the x-coordinate, while latitude values are the y-coordinate
        this.coordinates = new Point2D.Float();
        this.coordinates.setLocation(coordinates[1], coordinates[0]);
    }
    public static String getJsonPropertyNames(){
         var fields = Arrays.stream(Country.class.getDeclaredFields()).map(field -> {
            if(field.isAnnotationPresent(JsonProperty.class)) return field.getAnnotation(JsonProperty.class).value();
            return field.getName();
        }).toList();
        return String.join(",", fields);
    }
}
