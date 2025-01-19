package ru.hofftech.consolepackages.service.billing;

import ru.hofftech.consolepackages.model.Truck;

import java.util.List;

public interface PackageBillingService {
    void creatLoadPackageBill(List<Truck> trucks, String clientId);

    void creatUnloadPackageBill(List<Truck> trucks, String clientId);
}
