package ru.hofftech.consolepackages.service.billing;

import ru.hofftech.consolepackages.datastorage.model.entity.OperationType;
import ru.hofftech.consolepackages.model.Truck;
import ru.hofftech.consolepackages.model.dto.billing.BillingByUserSummaryResponse;

import java.time.LocalDate;
import java.util.List;

public interface PackageBillingService {
    void creatPackageBill(List<Truck> trucks, String clientId, OperationType operationType);

    List<BillingByUserSummaryResponse> returnBillingSummaryByClient(String clientId, LocalDate fromDate, LocalDate toDate);
}
