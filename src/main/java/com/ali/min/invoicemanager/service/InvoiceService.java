package com.ali.min.invoicemanager.service;

import com.ali.min.invoicemanager.dto.InvoiceDTO;
import com.ali.min.invoicemanager.entity.InvoiceEntity;
import com.ali.min.invoicemanager.enums.Status;
import com.ali.min.invoicemanager.mapper.InvoiceMapper;
import com.ali.min.invoicemanager.repository.InvoiceRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for Invoice operations
 *
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class InvoiceService {
    @Value("${invoice.limit:200}")
    @Getter
    @Setter
    private BigDecimal limit;

    private final InvoiceRepository invoiceRepository;

    private final InvoiceMapper invoiceMapper;

    private final ActivityService activityService;

    /**
     * Save invoice
     *
     * @param invoiceDTO invoice to be saved
     * @return saved invoice
     */
    public InvoiceDTO saveInvoice(InvoiceDTO invoiceDTO) {
        if (!invoiceRepository.isWithinThreshold(invoiceDTO.getAmount(), limit, invoiceDTO.getEmail())){
            activityService.saveActivity(invoiceDTO, Status.FAILED);
            throw new IllegalArgumentException("Amount exceeds the limit");
        }
        InvoiceEntity invoiceEntity  = invoiceMapper.toEntity(invoiceDTO);
        InvoiceDTO savedInvoiceDTO = invoiceMapper.toDTO(invoiceRepository.save(invoiceEntity));
        activityService.saveActivity(savedInvoiceDTO, Status.SUCCESS);
        return savedInvoiceDTO;
    }

    /**
     * Get all invoices
     *
     * @return List of invoices
     */
    public List<InvoiceDTO> getInvoices() {
        return invoiceRepository.findAll().stream().map(invoiceMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * Get invoice by id
     *
     * @param id invoice id
     * @return invoice
     */
    public InvoiceDTO getInvoice(Long id) {
        InvoiceEntity invoiceEntity = invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invoice not found"));
        return invoiceMapper.toDTO(invoiceEntity);
    }
}
