package com.uade.tpo.Marketplace.controllers.categories.location;

import java.util.List;

import lombok.Data;


@Data

public class ProvinceResponse {

    private List<ProvinciaData> provincias;

    @Data
    public static class ProvinciaData {
        private String id;
        private String nombre;
    }
}
