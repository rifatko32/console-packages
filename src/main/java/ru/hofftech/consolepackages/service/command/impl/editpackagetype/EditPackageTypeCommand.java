package ru.hofftech.consolepackages.service.command.impl.editpackagetype;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.command.Command;

/**
 * Command to edit a package type.
 */
@RequiredArgsConstructor
public class EditPackageTypeCommand implements Command {
    private final EditPackageTypeContext context;
    private final PackageTypeRepository packageTypeRepository;

    /**
     * Edits a package type.
     */
    @Override
    public void execute() {
        var packageType = packageTypeRepository.find(context.name());

        if (packageType == null) {
            return;
        }

        if (context.form() != null && !context.form().isEmpty()) {
            packageType.setForm(context.form());
        }

        if (context.description() != null && !context.description().isEmpty()) {
            packageType.setDescriptionNumber(context.description());
        }

        packageTypeRepository.updatePackageType(packageType);
    }
}
