package ru.hofftech.consolepackages.service.command.impl.editpackagetype;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.repository.PackageTypeRepository;
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
        var packageType = packageTypeRepository.findById(context.id()).orElse(null);

        if (packageType == null) {
            return;
        }

        if (context.form() != null && !context.form().isEmpty()) {
            packageType.setForm(context.form());
        }

        if (context.description() != null && !context.description().isEmpty()) {
            packageType.setDescriptionNumber(context.description());
        }

        packageTypeRepository.save(packageType);
    }
}
