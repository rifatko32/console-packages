package ru.hofftech.billing.stream;

import lombok.RequiredArgsConstructor;
import ru.hofftech.billing.model.dto.CreatePackageBillRequest;
import ru.hofftech.billing.service.PackageBillingService;

@RequiredArgsConstructor
public class BillingStreamer {
    
    private final PackageBillingService packageBillingService;

    public void handle(CreatePackageBillRequest request) {
        packageBillingService.creatPackageBill(request);
    }
}
