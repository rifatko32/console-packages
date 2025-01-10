package ru.hofftech.consolepackages.service.command.impl.findpackagetype;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.command.Command;

import java.util.ArrayList;

/**
 * Command to find package type by name.
 */
@RequiredArgsConstructor
public class FindPackageTypeCommand implements Command {

    private final FindPackageTypeContext context;
    private final PackageTypeRepository packageTypeRepository;

    @Override
    public void execute() {
        var result = new ArrayList<PackageType>();

        if (context.getName() == null || context.getName().isEmpty()) {
            result.addAll(packageTypeRepository.findAll());
        }
        else {
            var packageType = packageTypeRepository.find(context.getName());

            if (packageType == null) {
                return;
            }

            result.add(packageType);
        }

        context.setResult(result);
    }
}
