package ru.hofftech.consolepackages.model.dto.unloadtruck;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UnloadTruckResponse {
    private List<PackageCountResponse> packageCountResponses = new ArrayList<>(); // <packageTypeName, count>

    public void addPackageCountResponse(PackageCountResponse packageCountResponse) {
        packageCountResponses.add(packageCountResponse); }
}
