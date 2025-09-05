package com.uade.tpo.Marketplace.service.location;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uade.tpo.Marketplace.controllers.categories.location.CityResponse;
import com.uade.tpo.Marketplace.controllers.categories.location.ProvinceResponse;
import com.uade.tpo.Marketplace.entity.City;
import com.uade.tpo.Marketplace.entity.Province;
import com.uade.tpo.Marketplace.repository.city.CityRepository;
import com.uade.tpo.Marketplace.repository.province.ProvinceRepository;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private ProvinceRepository provinciaRepository;

    @Autowired
    private CityRepository municipioRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void importarCityProvince() {
        String urlProvinces = "https://apis.datos.gob.ar/georef/api/provincias?campos=id,nombre";
        ProvinceResponse response = restTemplate.getForObject(urlProvinces, ProvinceResponse.class);

        if (response != null && response.getProvincias() != null) {
            for (ProvinceResponse.ProvinciaData provData : response.getProvincias()) {
                Province provincia = new Province();
                provincia.setId(provData.getId());
                provincia.setNombre(provData.getNombre());
                provinciaRepository.save(provincia);

                // Import cities (municipios) of the province
                String urlCities = "https://apis.datos.gob.ar/georef/api/municipios?provincia="
                    + provData.getNombre() + "&campos=id,nombre&max=5000";
                CityResponse citiesResp = restTemplate.getForObject(urlCities, CityResponse.class);

                if (citiesResp != null && citiesResp.getCities() != null) {
                    for (CityResponse.CityData cityData : citiesResp.getCities()) {
                        City municipio = new City();
                        municipio.setId(cityData.getId());
                        municipio.setNombre(cityData.getNombre());
                        municipio.setProvince(provincia);

                        municipioRepository.save(municipio);
                    }
                }
            }
        }
    }
}


