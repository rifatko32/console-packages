package ru.hofftech.consolepackages.service.command.impl.deletepackagetype;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.command.Command;


/**
 * Command to delete a package type.
 */
@RequiredArgsConstructor
public class DeletePackageTypeCommand implements Command {

    private final DeletePackageTypeContext context;
    private final PackageTypeRepository packageTypeRepository;

    @Override
    public void execute() {
        packageTypeRepository.delete(context.name());
    }
}
