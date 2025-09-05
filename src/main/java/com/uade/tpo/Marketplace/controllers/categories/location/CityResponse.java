package com.uade.tpo.Marketplace.controllers.categories.location;


import java.util.List;

import lombok.Data;

@Data

public class CityResponse {
    private List<CityData> cities;

    @Data
    public static class CityData {
        private String id;
        private String nombre;
    }
}

