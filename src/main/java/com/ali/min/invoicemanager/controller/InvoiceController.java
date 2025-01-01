package com.ali.min.invoicemanager.controller;


import com.ali.min.invoicemanager.dto.InvoiceDTO;
import com.ali.min.invoicemanager.dto.ResultDTO;
import com.ali.min.invoicemanager.enums.Status;
import com.ali.min.invoicemanager.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling invoice related operations
 *
 * @version 1.0
 */
@RestController
@RequestMapping("/v1/invoice")
@RequiredArgsConstructor
@SecurityRequirement(name = "inv-man-auth")
public class InvoiceController {

    private final InvoiceService invoiceService;

    /**
     * Saves the invoice
     *
     * @param invoiceDTO invoice to be validated
     * @return saved invoice
     */
    @PostMapping
    @Operation(summary = "Save invoice")
    public ResponseEntity<ResultDTO> save(@RequestBody InvoiceDTO invoiceDTO) {
        InvoiceDTO savedInvoice = invoiceService.saveInvoice(invoiceDTO);
        return ResponseEntity.ok(
                ResultDTO.builder()
                        .message("Invoice saved successfully")
                        .status(Status.SUCCESS)
                        .data(savedInvoice).build());
    }

    /**
     * Get invoice by id
     *
     * @param id invoice id
     * @return invoice
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get invoice by id")
    public ResponseEntity<ResultDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(
                ResultDTO.builder()
                        .data(invoiceService.getInvoice(id))
                        .status(Status.SUCCESS)
                        .message("Invoice retrieved successfully").build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResultDTO> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest()
                .body(ResultDTO.builder().message(e.getMessage()).status(Status.FAILED).build());
    }
}
