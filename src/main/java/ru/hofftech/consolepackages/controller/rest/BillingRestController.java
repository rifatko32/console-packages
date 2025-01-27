package ru.hofftech.consolepackages.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hofftech.consolepackages.model.dto.billing.BillingByUserSummaryResponse;
import ru.hofftech.consolepackages.service.billing.PackageBillingService;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for billing operations.
 */
@Tag(name = "Billing Controller", description = "REST API для управления биллингом клиентов")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/billing")
public class BillingRestController {

    private final PackageBillingService packageBillingService;

    /**
     * Returns a billing report for a user within a specified period.
     *
     * @param clientId the user id
     * @param fromDate the start date of the period
     * @param toDate   the end date of the period
     * @return a list of {@link BillingByUserSummaryResponse} objects
     */
    @GetMapping("/{clientId}")
    @Operation(summary = "Возвращает список счетов по клиенту")
    public ResponseEntity<List<BillingByUserSummaryResponse>> returnBillingByClient(
            @PathVariable String clientId,
            @RequestParam @NotNull LocalDate fromDate,
            @RequestParam @NotNull LocalDate toDate
    ) {
        return new ResponseEntity<>(packageBillingService.returnBillingSummaryByClient(clientId, fromDate, toDate), HttpStatus.OK);
    }
}
