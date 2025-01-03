package ru.hofftech.consolepackages.service.command.impl.findpackagetype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;
import ru.hofftech.consolepackages.service.command.CommandContextWithResult;

import java.util.ArrayList;

@Getter
@Setter
@RequiredArgsConstructor
public class FindPackageTypeContext extends CommandContextWithResult<ArrayList<PackageType>> {

    private final String name;
}
