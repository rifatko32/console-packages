package ru.hofftech.consolepackages.service.command.impl.createpackagetype;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.command.Command;

@Slf4j
@RequiredArgsConstructor
public class CreatePackageTypeCommand implements Command {

    private final CreatePackageTypeContext context;
    private final PackageTypeRepository packageTypeRepository;

    @Override
    public void execute() {
        packageTypeRepository.create(
                context.name(),
                context.form(),
                context.description());
    }
}
