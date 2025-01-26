package ru.hofftech.consolepackages.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hofftech.consolepackages.model.dto.unloadtruck.UnloadTruckDto;
import ru.hofftech.consolepackages.model.dto.unloadtruck.UnloadTruckResponse;
import ru.hofftech.consolepackages.service.truck.UnloadTruckService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trucks")
public class UnloadTruckRestController {

    private UnloadTruckService unloadTruckService;

    @PostMapping
    public ResponseEntity<UnloadTruckResponse> unloadTruck(@RequestBody UnloadTruckDto unloadTruckDto) {
        return new ResponseEntity<>(unloadTruckService.unloadTruck(unloadTruckDto), HttpStatus.OK);
    }
}
