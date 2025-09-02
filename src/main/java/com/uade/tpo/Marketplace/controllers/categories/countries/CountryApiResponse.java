package com.uade.tpo.Marketplace.controllers.categories.countries;

import java.util.List;

import lombok.Data;

@Data

public class CountryApiResponse {
    private boolean error;
    private String msg;
    private List<CountryData> data;

    @Data
    public static class CountryData {
        private String name;
        private String iso2;
        private List<StateData> states;
    }

    @Data
    public static class StateData {
        private String name;
    }

}
