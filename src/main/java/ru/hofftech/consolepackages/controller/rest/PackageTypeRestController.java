package ru.hofftech.consolepackages.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hofftech.consolepackages.model.dto.packagetype.CreatePackageTypeDto;
import ru.hofftech.consolepackages.model.dto.packagetype.EditPackageTypeDto;
import ru.hofftech.consolepackages.model.dto.packagetype.PackageTypeResponse;
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
     * @param id Package type id
     * @return Package type
     */
    @Operation(summary = "Возвращает тип пакета по имени")
    @GetMapping("{id}")
    public ResponseEntity<PackageTypeResponse> findPackageType(@PathVariable Long id) {
        return ResponseEntity.ok(packageTypeService.findPackageType(id));
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
    public ResponseEntity<List<PackageTypeResponse>> findPackageTypes(@NotNull Integer page, @NotNull Integer size) {
        return ResponseEntity.ok(packageTypeService.findPackageTypes(page, size));
    }

    /**
     * Creates a new package type.
     *
     * @param createPackageTypeDto Package type creation data
     * @return Created package type
     */
    @Operation(summary = "Создает тип пакета")
    @PostMapping()
    public ResponseEntity<PackageTypeResponse> createPackageType(@RequestBody @NotNull CreatePackageTypeDto createPackageTypeDto) {
        return ResponseEntity.ok(packageTypeService.createPackageType(createPackageTypeDto));
    }

    /**
     * Deletes a package type.
     *
     * @param id Package type id
     * @return ResponseEntity with status 204 No Content
     */
    @Operation(summary = "Удаляет тип пакета")
    @DeleteMapping("{id}")
    public ResponseEntity.HeadersBuilder<?> deletePackageType(@PathVariable Long id) {
        packageTypeService.deletePackageType(id);
        return ResponseEntity.noContent();
    }

    /**
     * Edits a package type.
     *
     * @param editPackageTypeDto Data for editing the package type
     * @return Updated package type
     */
    @Operation(summary = "Редактирует тип пакета")
    @PutMapping("{id}")
    public ResponseEntity<PackageTypeResponse> editPackageType(@PathVariable Long id, @RequestBody @NotNull EditPackageTypeDto editPackageTypeDto) {
        return ResponseEntity.ok(packageTypeService.editPackageType(id, editPackageTypeDto));
    }
}
