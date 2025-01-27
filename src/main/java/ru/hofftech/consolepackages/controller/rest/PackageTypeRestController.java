package ru.hofftech.consolepackages.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;
import ru.hofftech.consolepackages.model.dto.packagetype.CreatePackageTypeDto;
import ru.hofftech.consolepackages.model.dto.packagetype.EditPackageTypeDto;
import ru.hofftech.consolepackages.service.packagetype.PackageTypeService;

import java.util.List;

/**
 * REST controller to manage package types.
 */
@Tag(name = "PackageType Controller", description = "REST API для управления типами посылок")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/package-types")
public class PackageTypeRestController {

    private final PackageTypeService packageTypeService;

    /**
     * Finds a package type by name.
     *
     * @param name Package type name
     * @return Package type
     */
    @Operation(summary = "Возвращает тип пакета по имени")
    @GetMapping("{name}")
    public ResponseEntity<PackageType> findPackageType(@PathVariable String name) {
        return new ResponseEntity<>(packageTypeService.findPackageType(name), HttpStatus.OK);
    }

    /**
     * Finds all package types.
     *
     * @param page Page number
     * @param size Page size
     * @return List of package types
     */
    @Operation(summary = "Возвращает типы пакетов")
    @GetMapping()
    public ResponseEntity<List<PackageType>> findPackageTypes(Integer page, Integer size) {
        return new ResponseEntity<>(packageTypeService.findPackageTypes(page, size), HttpStatus.OK);
    }

    /**
     * Creates a new package type.
     *
     * @param createPackageTypeDto Package type creation data
     * @return Created package type
     */
    @Operation(summary = "Создает тип пакета")
    @PostMapping()
    public ResponseEntity<PackageType> createPackageType(@RequestBody @NotNull CreatePackageTypeDto createPackageTypeDto) {
        return new ResponseEntity<>(packageTypeService.createPackageType(createPackageTypeDto), HttpStatus.OK);
    }

    /**
     * Deletes a package type.
     *
     * @param name Package type name
     * @return ResponseEntity with status 204 No Content
     */
    @Operation(summary = "Удаляет тип пакета")
    @DeleteMapping("{name}")
    public ResponseEntity deletePackageType(@PathVariable String name) {
        packageTypeService.deletePackageType(name);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * Edits a package type.
     *
     * @param editPackageTypeDto Data for editing the package type
     * @return Updated package type
     */
    @Operation(summary = "Редактирует тип пакета")
    @PutMapping("{name}")
    public ResponseEntity<PackageType> editPackageType(@RequestBody @NotNull EditPackageTypeDto editPackageTypeDto) {
        return new ResponseEntity<>(packageTypeService.editPackageType(editPackageTypeDto), HttpStatus.OK);
    }
}
