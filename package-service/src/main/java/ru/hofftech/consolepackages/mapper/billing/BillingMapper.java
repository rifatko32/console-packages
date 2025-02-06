package ru.hofftech.consolepackages.mapper.billing;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hofftech.consolepackages.model.Package;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.model.dto.billing.PackageBillDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BillingMapper {


    @Mapping(source = "id", target = "truckId")
    @Mapping(target = "packageVolumes", expression = "java(calculatePackageVolumes(truck.getPackages()))")
    PackageBillDto mapTruckBillDto(Truck truck);

    List<PackageBillDto> mapTrucksToBillDtos(List<Truck> trucks);

    default List<Integer> calculatePackageVolumes(List<ru.hofftech.consolepackages.model.Package> packages) {
        return packages.stream()
                .map(Package::calcVolume)
                .toList();
    }
}
