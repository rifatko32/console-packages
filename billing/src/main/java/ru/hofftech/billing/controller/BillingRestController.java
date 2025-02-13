package ru.hofftech.billing.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hofftech.billing.model.dto.BillingResponse;
import ru.hofftech.billing.model.dto.GenerateReportByPeriodResponse;
import ru.hofftech.billing.service.PackageBillingService;
import ru.hofftech.billing.utils.DateUtils;

import java.time.LocalDate;

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
     * @param userId the user id
     * @param fromDate the start date of the period
     * @param toDate   the end date of the period
     * @return a list of {@link BillingResponse} objects
     */
    @GetMapping("{userId}")
    @Operation(summary = "Возвращает список счетов по клиенту")
    public ResponseEntity<GenerateReportByPeriodResponse> returnBillingByClient(
            @PathVariable String userId,
            @RequestParam @NotNull @DateTimeFormat(pattern = DateUtils.DATE_FORMAT) LocalDate fromDate,
            @RequestParam @NotNull @DateTimeFormat(pattern = DateUtils.DATE_FORMAT) LocalDate toDate
    ) {
        return ResponseEntity.ok(packageBillingService.generateReportByPeriod(userId, fromDate, toDate));

    }
}
