package ru.hofftech.consolepackages.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.hofftech.consolepackages.model.dto.placepackage.PlacePackagesFromFileDto;
import ru.hofftech.consolepackages.model.dto.placepackage.PlacePackagesFromTextDto;
import ru.hofftech.consolepackages.model.dto.placepackage.PlacePackagesResponse;
import ru.hofftech.consolepackages.service.packageitem.PlacePackageService;

@Tag(name = "LoadPackage Controller", description = "REST API для загрузки посылками")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/packages")
public class LoadPackageRestController {

    private final PlacePackageService placePackageService;

    @Operation(summary = "Грузит посылки по текстовому описанию")
    @PostMapping
    public ResponseEntity<PlacePackagesResponse> placePackagesFromText(@NotNull @RequestBody PlacePackagesFromTextDto loadPackagesFromTextDto) {
        return new ResponseEntity<>(placePackageService.placePackagesFromString(loadPackagesFromTextDto), HttpStatus.OK);
    }

    @Operation(summary = "Грузит посылки из файла")
    @PostMapping("/from-file")
    public ResponseEntity<PlacePackagesResponse> placePackagesFromFile(
            @RequestPart("file") MultipartFile file,
            @RequestBody PlacePackagesFromFileDto loadPackagesFromFileDto) {
        return new ResponseEntity<>(placePackageService.placePackagesFromFile(loadPackagesFromFileDto, file), HttpStatus.OK);
    }
}
