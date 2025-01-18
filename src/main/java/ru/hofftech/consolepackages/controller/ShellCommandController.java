package ru.hofftech.consolepackages.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;

import java.util.List;

@RequiredArgsConstructor
@ShellComponent
public class ShellCommandController {

    private final PackageTypeRepository packageTypeRepository;

    @ShellMethod(key = "packages")
    public List<String> returnPackageTypes(
    ) {
        return packageTypeRepository.findAll().stream().map(PackageType::getName).toList();
    }
}
