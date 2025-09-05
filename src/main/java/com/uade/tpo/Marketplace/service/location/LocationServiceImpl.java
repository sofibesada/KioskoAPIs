package com.uade.tpo.Marketplace.service.location;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uade.tpo.Marketplace.controllers.categories.location.ProvinceResponse;
import com.uade.tpo.Marketplace.entity.Province;
import com.uade.tpo.Marketplace.repository.province.ProvinceRepository;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private ProvinceRepository provinciaRepository;


    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void importarProvince() {
        String urlProvinces = "https://apis.datos.gob.ar/georef/api/provincias?campos=id,nombre";
        ProvinceResponse response = restTemplate.getForObject(urlProvinces, ProvinceResponse.class);

        if (response != null && response.getProvincias() != null) {
            for (ProvinceResponse.ProvinciaData provData : response.getProvincias()) {
                Province provincia = new Province();
                provincia.setId(provData.getId());
                provincia.setNombre(provData.getNombre());
                provinciaRepository.save(provincia);
            }
        }
    }
}


