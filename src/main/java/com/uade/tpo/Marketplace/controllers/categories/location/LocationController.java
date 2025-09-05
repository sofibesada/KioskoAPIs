package com.uade.tpo.Marketplace.controllers.categories.location;

import com.uade.tpo.Marketplace.service.location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/import")
    public ResponseEntity<String> importLocations() {
        locationService.importarProvince();
        return ResponseEntity.ok("Provincias importadas correctamente");
    }
}


